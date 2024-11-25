package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.sale.CreateSaleDTO;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;
import net.axel.citronix.domain.dtos.sale.UpdateSaleDTO;

import java.util.List;

public interface SaleService {

    SaleResponseDTO create(CreateSaleDTO dto);

    SaleResponseDTO update(Long id, UpdateSaleDTO dto);

    SaleResponseDTO findById(Long id);

    List<SaleResponseDTO> findAll(int page, int size);

    void delete(Long id);
}
