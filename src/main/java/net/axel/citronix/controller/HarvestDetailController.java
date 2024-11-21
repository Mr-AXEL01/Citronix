package net.axel.citronix.controller;

import lombok.RequiredArgsConstructor;
import net.axel.citronix.service.HarvestDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HarvestDetailController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class HarvestDetailController {

    public final static String CONTROLLER_PATH = "/api/v1/harvest-details";

    private final HarvestDetailService service;
}
