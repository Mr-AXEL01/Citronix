package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;
import net.axel.citronix.exception.domains.ResourceNotFoundException;
import net.axel.citronix.mapper.SaleMapper;
import net.axel.citronix.repository.SaleRepository;
import net.axel.citronix.service.SaleService;
import org.springframework.stereotype.Service;

@Service
@Transactional

@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;

    @Override
    public SaleResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("sale", id));
    }
}
