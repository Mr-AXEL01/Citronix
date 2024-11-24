package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailRequestDTO;
import net.axel.citronix.domain.dtos.harvestDetail.HarvestDetailResponseDTO;
import net.axel.citronix.domain.embeddeds.HarvestDetailKey;
import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.entities.HarvestDetail;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.HarvestDetailMapper;
import net.axel.citronix.repository.HarvestDetailRepository;
import net.axel.citronix.service.HarvestDetailService;
import org.springframework.stereotype.Service;

@Service
@Transactional

@RequiredArgsConstructor
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository repository;
    private final HarvestDetailMapper mapper;

    @Override
    public HarvestDetailResponseDTO update(HarvestDetailRequestDTO dto) {
        HarvestDetailKey id = new HarvestDetailKey(dto.treeId(), dto.harvestId());
        HarvestDetail existingHarvestDetail = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HarvestDetail", id));

        if (dto.quantity() != null && !dto.quantity().equals(existingHarvestDetail.getQuantity())) {
            double newQuantity = dto.quantity();
            double oldQuantity = existingHarvestDetail.getQuantity();
            double difference = newQuantity - oldQuantity;

            existingHarvestDetail.setQuantity(newQuantity);

            Harvest harvest = existingHarvestDetail.getHarvest();
            harvest.setTotalQuantity(harvest.getTotalQuantity() + difference);
        }

        return mapper.toResponseDto(existingHarvestDetail);
    }
}
