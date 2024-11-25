package net.axel.citronix.service.impl;

import net.axel.citronix.domain.dtos.farm.CreateFarmDTO;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.UpdateFarmDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.repository.FarmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FarmServiceImplTest {

    @Mock
    private FarmRepository repository;

    @Mock
    private FarmMapper mapper;

    @InjectMocks
    private FarmServiceImpl service;

    private Farm farm;
    private FarmResponseDTO responseDTO;
    private CreateFarmDTO createDTO;
    private UpdateFarmDTO updateDTO;

    @BeforeEach
    void setUp() {
        farm = new Farm(1L, "farm_1", "location_1", 70.0, LocalDate.of(2023, 1, 1), Collections.emptyList());
        responseDTO = new FarmResponseDTO(1L, "farm_1", "location_1", 70.0, LocalDate.of(2023, 1, 1), Collections.emptyList());
        createDTO = new CreateFarmDTO("farm_1", "location_1", 70.0, LocalDate.of(2023, 1, 1));
        updateDTO = new UpdateFarmDTO("updated_farm_1", "updated_location_1", 100.0, LocalDate.of(2024, 1, 1));
    }

    @Test
    void givenExistingFarmId_whenFindById_thenReturnFarmResponseDTO() {
        when(repository.findById(farm.getId())).thenReturn(Optional.of(farm));
        when(mapper.toResponseDto(farm)).thenReturn(responseDTO);

        var result = service.findById(farm.getId());

        assertEquals(responseDTO, result);
    }

    @Test
    void givenNonExistingFarmId_whenFindById_thenThrowResourceNotFoundException() {
        when(repository.findById(farm.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(farm.getId()));
    }

    @Test
    void givenFarmsInDatabase_whenFindAll_thenReturnFarmResponseDTOList() {
        Page<Farm> farmPage = new PageImpl<>(Collections.singletonList(farm));
        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(farmPage);
        when(mapper.toResponseDto(farm)).thenReturn(responseDTO);

        var result = service.findAll(0, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
    }

    @Test
    void givenValidCreateFarmDTO_whenCreate_thenReturnFarmResponseDTO() {
        when(mapper.toEntity(createDTO)).thenReturn(farm);
        when(repository.save(farm)).thenReturn(farm);
        when(mapper.toResponseDto(farm)).thenReturn(responseDTO);

        var result = service.create(createDTO);

        assertEquals(responseDTO, result);
    }

    @Test
    void givenValidUpdateFarmDTO_whenUpdate_thenReturnUpdatedFarmResponseDTO() {
        when(repository.findById(farm.getId())).thenReturn(Optional.of(farm));
        when(mapper.toResponseDto(farm)).thenReturn(responseDTO);

        var result = service.update(farm.getId(), updateDTO);

        assertEquals(responseDTO, result);
        assertEquals("updated_farm_1", farm.getName());
        assertEquals("updated_location_1", farm.getLocation());
        assertEquals(100.0, farm.getSize());
        assertEquals(LocalDate.of(2024, 1, 1), farm.getCreationDate());
    }

    @Test
    void givenNonExistingFarmId_whenUpdate_thenThrowResourceNotFoundException() {
        when(repository.findById(farm.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(farm.getId(), updateDTO));
    }

    @Test
    void givenExistingFarmId_whenDelete_thenRemoveFarm() {
        when(repository.existsById(farm.getId())).thenReturn(true);

        service.delete(farm.getId());
    }

    @Test
    void givenNonExistingFarmId_whenDelete_thenThrowResourceNotFoundException() {
        when(repository.existsById(farm.getId())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(farm.getId()));
    }
}