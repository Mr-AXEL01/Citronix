package net.axel.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.sale.CreateSaleDTO;
import net.axel.citronix.domain.dtos.sale.SaleResponseDTO;
import net.axel.citronix.domain.dtos.sale.UpdateSaleDTO;
import net.axel.citronix.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SaleController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class SaleController {

    public final static String CONTROLLER_PATH = "/api/v1/sales";

    private final SaleService service;

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> findById(@PathVariable("id") Long id) {
        SaleResponseDTO sale = service.findById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> findAll() {
        List<SaleResponseDTO> sales = service.findAll();
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> create(@RequestBody @Valid CreateSaleDTO dto) {
        SaleResponseDTO sale = service.create(dto);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid UpdateSaleDTO dto) {
        SaleResponseDTO updatedSale = service.update(id, dto);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
