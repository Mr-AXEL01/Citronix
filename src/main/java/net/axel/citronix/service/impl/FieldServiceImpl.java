package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.domain.entities.Field;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.mapper.FieldMapper;
import net.axel.citronix.repository.FieldRepository;
import net.axel.citronix.service.FarmService;
import net.axel.citronix.service.FieldService;
import org.springframework.stereotype.Service;

@Service
@Transactional

@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository repository;
    private final FieldMapper mapper;
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    @Override
    public FieldResponseDTO create(CreateFieldDTO dto) {
        FarmResponseDTO farmResponse = farmService.findById(dto.farmId());
        Farm farm = farmMapper.toEntityFromResponseDto(farmResponse);

        if (dto.area() > (farm.getSize() * 0.5)) {
            throw new BusinessException("Field area cannot exceed 50% of the farm's total size.");
        }

        if (farm.getFields().size() > 10) {
            throw new BusinessException("A farm cannot contain more than 10 fields.");
        }

        Field field = mapper.toEntity(dto)
                .setFarm(farm);

        Field savedRepository = repository.save(field);

        return mapper.toResponseDto(savedRepository);
    }
}
