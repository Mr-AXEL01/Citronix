package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.domain.dtos.sale.CreateSaleDTO;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;
import net.axel.citronix.domain.dtos.sale.UpdateSaleDTO;
import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.entities.Sale;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.HarvestMapper;
import net.axel.citronix.mapper.SaleMapper;
import net.axel.citronix.repository.SaleRepository;
import net.axel.citronix.service.HarvestService;
import net.axel.citronix.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;
    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    @Override
    public SaleResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("sale", id));
    }

    @Override
    public List<SaleResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Sale> sales = repository.findAll(pageable);

        return sales.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public SaleResponseDTO create(CreateSaleDTO dto) {
        Harvest harvest = getHarvest(dto.harvestId());

        checkIfHarvestSold(harvest);

        validateBusinessLogic(dto.date().isBefore(harvest.getHarvestDate()), "The Sale date cant be before the harvest date.");

        Sale sale = mapper.toEntity(dto)
                .setHarvest(harvest);

        Sale savedSale = repository.save(sale);

        return mapper.toResponseDto(savedSale);
    }

    @Override
    public SaleResponseDTO update(Long id, UpdateSaleDTO dto) {
        Sale existingSale = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No sale found with the ID provided"));

        if (dto.harvestId() != null && !dto.harvestId().equals(existingSale.getHarvest().getId())) {
            Harvest harvest = getHarvest(dto.harvestId());

            checkIfHarvestSold(harvest);

            existingSale.setHarvest(harvest);
        }

        if (dto.client() != null && !dto.client().equals(existingSale.getClient())) {
            existingSale.setClient(dto.client());
        }

        if (dto.date() != null && !dto.date().equals(existingSale.getDate())) {
            validateBusinessLogic(dto.date().isBefore(existingSale.getHarvest().getHarvestDate()), "The Sale date cant be before the harvest date.");
            existingSale.setDate(dto.date());
        }

        if (dto.unitePrice() != null && !dto.unitePrice().equals(existingSale.getUnitPrice())) {
            existingSale.setUnitPrice(dto.unitePrice());
        }

        return mapper.toResponseDto(existingSale);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't delete this Cause it's not found.");
        }
        repository.deleteById(id);
    }

    private Harvest getHarvest(Long harvestId) {
        return harvestMapper.toEntityFromResponseDto(
                harvestService.findById(harvestId)
        );
    }

    private void checkIfHarvestSold(Harvest harvest) {
        validateBusinessLogic(repository.existsByHarvest(harvest), "This harvest is already sold.");
    }

    private void validateBusinessLogic(boolean statement, String message) {
        if (statement) {
            throw new BusinessException(message);
        }
    }
}
