package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.harvest.CreateHarvestDTO;
import net.axel.citronix.domain.dtos.harvest.HarvestResponseDTO;
import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.enums.Season;
import net.axel.citronix.exception.domains.BusinessException;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.HarvestMapper;
import net.axel.citronix.repository.HarvestRepository;
import net.axel.citronix.service.HarvestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {
    
    private final HarvestRepository repository;
    private final HarvestMapper mapper;

    @Override
    public HarvestResponseDTO findById(Long id) {
        Harvest harvest = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Harvest", id));

        return mapper.toResponseDto(harvest);
    }

    @Override
    public List<HarvestResponseDTO> findAll() {
        List<Harvest> harvestEntities = repository.findAll();

        if (harvestEntities.isEmpty()) {
            throw new ResourceNotFoundException("No harvest found.");
        }

        return harvestEntities.stream()
                .map(mapper::toResponseDto)
                .toList();
    }
    
    @Override
    public HarvestResponseDTO create(CreateHarvestDTO dto) {
        Season season = determineSeason(dto.harvestDate());

        if (repository.existsBySeason(season)) {
            throw new BusinessException("Already exists a harvest in this season :" + season);
        }

        Harvest harvest = mapper.toEntity(dto)
                .setSeason(season)
                .setTotalQuantity(0.0);

        Harvest savedHarvest = repository.save(harvest);

        return mapper.toResponseDto(savedHarvest);
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
