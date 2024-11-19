package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.FarmRequestDTO;
import net.axel.citronix.domain.dtos.FarmResponseDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.repository.FarmRepository;
import net.axel.citronix.service.FarmService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional

@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository repository;
    private final FarmMapper mapper;


    @Override
    public FarmResponseDTO create(FarmRequestDTO dto) {
        Farm farm = mapper.toEntity(dto)
                .setCreationDate(getDefult(dto.creationDate(), LocalDate.now()));

        Farm savedFarm = repository.save(farm);

        return mapper.toResponseDto(savedFarm);
    }

    @Override
    public FarmResponseDTO update(Long id, FarmRequestDTO dto) {
        Farm farm = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm", id));

        farm.setLocation(dto.location())
                .setSize(dto.size())
                .setCreationDate(dto.creationDate());

        Farm savedFarm = repository.save(farm);
        return mapper.toResponseDto(savedFarm);
    }

    private <T> T getDefult(T v, T dv) {
        return v != null ? v : dv;
    }
}
