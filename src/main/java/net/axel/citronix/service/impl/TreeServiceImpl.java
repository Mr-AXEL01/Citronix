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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static net.axel.citronix.domain.entities.Tree.getaDouble;
import static net.axel.citronix.domain.entities.Tree.getaInteger;

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
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Tree", id));

    }

    @Override
    public List<TreeResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Tree> trees = repository.findAll(pageable);

        return trees.stream()
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

        return mapper.toResponseDto(existingTree);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't delete non-exists Tree.");
        }
        repository.deleteById(id);
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

        if (plantingDate.isBefore(field.getFarm().getCreationDate())) {
            throw new BusinessException("The planting date of the tree can't before the creation date of the farm");
        }
    }

    private int generatedAge(LocalDate plantingDate) {
        return getaInteger(plantingDate);
    }

    private Field getField(Long fieldId) {
        FieldResponseDTO fieldResponse = fieldService.findById(fieldId);
        return fieldMapper.toEntityFromResponseDto(fieldResponse);
    }

    private double generatedProductivity(int age) {
        return getaDouble(age);
    }

}
