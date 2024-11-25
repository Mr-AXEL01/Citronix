package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.FarmSearchDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.repository.FarmRepository;
import net.axel.citronix.service.FarmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository repository;
    private final FarmMapper mapper;


    @Override
    public FarmResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Farm", id));
    }

    @Override
    public List<FarmResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farms = repository.findAll(pageable);
        
        return farms.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public List<FarmResponseDTO> searchFarms(FarmSearchDTO criteria) {
        return repository.searchFarms(criteria);
    }

    @Override
    public FarmResponseDTO create(CreateFarmDTO dto) {
        Farm farm = mapper.toEntity(dto)
                .setCreationDate(getDefult(dto.creationDate(), LocalDate.now()));

        Farm savedFarm = repository.save(farm);

        return mapper.toResponseDto(savedFarm);
    }

    @Override
    public FarmResponseDTO update(Long id, UpdateFarmDTO dto) {
        Farm existingFarm = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm", id));

        if (dto.name() != null) {
            existingFarm.setName(dto.name());
        }
        if (dto.location() != null) {
            existingFarm.setLocation(dto.location());
        }
        if (dto.size() != null) {
            existingFarm.setSize(dto.size());
        }
        if (dto.creationDate() != null) {
            existingFarm.setCreationDate(dto.creationDate());
        }

        return mapper.toResponseDto(existingFarm);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't remove non-exists farm");
        }
        repository.deleteById(id);
    }

    private <T> T getDefult(T v, T dv) {
        return v != null ? v : dv;
    }
}
