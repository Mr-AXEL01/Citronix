package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.field.CreateFieldDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.field.UpdateFieldDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.domain.entities.Field;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.mapper.FieldMapper;
import net.axel.citronix.repository.FieldRepository;
import net.axel.citronix.service.FarmService;
import net.axel.citronix.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository repository;
    private final FieldMapper mapper;
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    @Override
    public FieldResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Field", id));
    }

    @Override
    public Field findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Field", id));
    }

    @Override
    public List<FieldResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Field> fields = repository.findAll(pageable);

        return fields.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public FieldResponseDTO create(CreateFieldDTO dto) {
        Farm farm = getFarm(dto.farmId());

        fieldValidations(dto.area(), farm);

        Field field = mapper.toEntity(dto)
                .setFarm(farm);

        Field savedRepository = repository.save(field);

        return mapper.toResponseDto(savedRepository);
    }

    @Override
    public FieldResponseDTO update(Long id, UpdateFieldDTO dto) {
        Field existingField = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Field", id));

        Farm farm = getFarm(dto.farmId());

        if (dto.area() != null) {
            existingField.setArea(dto.area());
        }
        if (dto.farmId() != null) {
            existingField.setFarm(farm);
        }

        fieldValidations(dto.area(), farm);

        return mapper.toResponseDto(existingField);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't remove non-exists field.");
        }
        repository.deleteById(id);
    }

    private Farm getFarm(Long farmId) {
        FarmResponseDTO farmResponse = farmService.findById(farmId);
        return farmMapper.toEntityFromResponseDto(farmResponse);
    }

    private void fieldValidations(Double area, Farm farm) {
        if (area > (farm.getSize() * 0.5)) {
            throw new BusinessException("Field area cannot exceed 50% of the farm's total size.");
        }

        if (farm.getFields().size() > 10) {
            throw new BusinessException("A farm cannot contain more than 10 fields.");
        }

        double fieldsAreaSum = farm.getFields()
                .stream()
                .mapToDouble(Field::getArea)
                .sum() + area;

        if (fieldsAreaSum > farm.getSize()) {
            throw new BusinessException(farm.getName() + " Farm had insufficient space for this field.");
        }
    }
}
