package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.sale.CreateSaleDTO;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO create(CreateSaleDTO dto);

    SaleResponseDTO update();

    SaleResponseDTO findById(Long id);

    List<SaleResponseDTO> findAll();

    void delete(Long id);
}
