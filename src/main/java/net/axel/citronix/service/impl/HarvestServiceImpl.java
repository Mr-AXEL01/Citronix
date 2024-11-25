package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.field.FieldResponseDTO;
import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.domain.dtos.harvest.UpdateHarvestDTO;
import net.axel.citronix.domain.entities.Field;
import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.entities.HarvestDetail;
import net.axel.citronix.domain.entities.Tree;
import net.axel.citronix.domain.enums.Season;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.HarvestMapper;
import net.axel.citronix.repository.HarvestDetailRepository;
import net.axel.citronix.repository.HarvestRepository;
import net.axel.citronix.service.FieldService;
import net.axel.citronix.service.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository repository;
    private final HarvestMapper mapper;
    private final FieldService fieldService;

    @Override
    public HarvestResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest", id));

    }

    @Override
    public List<HarvestResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Harvest> harvests = repository.findAll(pageable);

        return harvests.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public HarvestResponseDTO create(CreateHarvestDTO dto) {
        Field field = fieldService.findEntityById(dto.fieldId());
        Season season = determineSeason(dto.harvestDate());
        int year = dto.harvestDate().getYear();

        if (repository.existsBySeasonAndYear(season, year)) {
            throw new BusinessException("Already exists a harvest in this season :" + season);
        }


        double total = field.getTrees().stream()
                .mapToDouble(tree -> {
                    int age = tree.getAge(tree.getPlantingDate());
                    return tree.getProductivity(age);
                })
                .sum();

        Harvest harvest = mapper.toEntity(dto)
                .setSeason(season)
                .setTotalQuantity(total);

        List<HarvestDetail> harvestDetails = field.getTrees()
                .stream()
                .map(tree -> {
                    int age = tree.getAge(tree.getPlantingDate());
                    return new HarvestDetail(tree, harvest, tree.getProductivity(age));
                })
                .toList();

        harvest.setHarvestDetails(harvestDetails);

        Harvest savedHarvest = repository.save(harvest);
        return mapper.toResponseDto(savedHarvest);
    }

    @Override
    public HarvestResponseDTO update(Long id, UpdateHarvestDTO dto) {
        Harvest existingHarvest = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest", id));

        if (dto.harvestDate() != null && dto.harvestDate() != existingHarvest.getHarvestDate()) {
            Season season = determineSeason(dto.harvestDate());
            int year = dto.harvestDate().getYear();

            if (repository.existsBySeasonAndYear(season, year)) {
                throw new BusinessException("Already exists a harvest in this season :" + season);
            }

            existingHarvest.setHarvestDate(dto.harvestDate())
                    .setSeason(season);
        }

        return mapper.toResponseDto(existingHarvest);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Can't delete non- exists harvest");
        }
        repository.deleteById(id);
    }

    private Season determineSeason(LocalDate date) {
        int month = date.getMonthValue();
        return switch (month) {
            case 12, 1, 2 -> Season.WINTER;
            case 3, 4, 5 -> Season.SPRING;
            case 6, 7, 8 -> Season.SUMMER;
            case 9, 10, 11 -> Season.AUTUMN;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

}
