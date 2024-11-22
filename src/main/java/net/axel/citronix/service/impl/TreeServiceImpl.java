package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;
import net.axel.citronix.domain.dtos.tree.UpdateTreeDTO;
import net.axel.citronix.domain.entities.Field;
import net.axel.citronix.domain.entities.Tree;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.FieldMapper;
import net.axel.citronix.mapper.TreeMapper;
import net.axel.citronix.repository.TreeRepository;
import net.axel.citronix.service.FieldService;
import net.axel.citronix.service.TreeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository repository;
    private final TreeMapper mapper;
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    @Override
    public TreeResponseDTO findById(Long id) {
        Tree tree = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", id));

        return mapper.toResponseDto(tree);
    }

    @Override
    public List<TreeResponseDTO> findAll() {
        List<Tree> treeEntities = repository.findAll();

        if (treeEntities.isEmpty()) {
            throw new ResourceNotFoundException("No trees found");
        }

        return treeEntities.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public TreeResponseDTO create(CreateTreeDTO dto) {

        Field field = getField(dto.fieldId());

        treeValidations(dto.plantingDate(), field);

        int age = generatedAge(dto.plantingDate());
        double productivity = generatedProductivity(age);

        Tree tree = mapper.toEntity(dto)
                .setAge(age)
                .setProductivity(productivity)
                .setField(field);

        Tree savedTree = repository.save(tree);

        return mapper.toResponseDto(savedTree);
    }

    @Override
    public TreeResponseDTO update(Long id, UpdateTreeDTO dto) {
        Tree existingTree = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", id));

        Field field = getField(dto.fieldId());

        treeValidations(dto.plantingDate(), field);

        if (dto.plantingDate() != null) {
            int age = generatedAge(dto.plantingDate());
            double productivity = generatedProductivity(age);

           existingTree.setPlantingDate(dto.plantingDate())
                   .setAge(age)
                   .setProductivity(productivity);
        }
        if (dto.fieldId() != null) {
            existingTree.setField(field);
        }

        Tree updatedTree = repository.save(existingTree);

        return mapper.toResponseDto(updatedTree);
    }

    private void treeValidations(LocalDate plantingDate, Field field) {
        double fieldArea = field.getArea();
        int fieldSize = field.getTrees().size();

        if ((fieldSize/fieldArea) >= 100) {
            throw new BusinessException("Field cannot accommodate more than 100 trees in each hectares.");
        }

        int month = plantingDate.getMonthValue();
        if (month < 3 || month > 5) {
            throw new BusinessException("Trees can only be planted between March and May.");
        }
    }

    private int generatedAge(LocalDate plantingDate) {
        return (int) ChronoUnit.YEARS.between(plantingDate, LocalDate.now());
    }

    private Field getField(Long fieldId) {
        FieldResponseDTO fieldResponse = fieldService.findById(fieldId);
        return fieldMapper.toEntityFromResponseDto(fieldResponse);
    }

    private double generatedProductivity(int age) {
        return switch (age) {
            case 1, 2 -> 2.5;
            case 3, 4, 5, 6, 7, 8, 9, 10 -> 12.0;
            case 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> 20.0;
            default -> 0.0;
        };
    }

}
