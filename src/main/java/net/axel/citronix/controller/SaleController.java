package net.axel.citronix.controller;

import lombok.RequiredArgsConstructor;
import net.axel.citronix.service.SaleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SaleController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class SaleController {

    public final static String CONTROLLER_PATH = "/api/v1/sales";

    private final SaleService service;
}
