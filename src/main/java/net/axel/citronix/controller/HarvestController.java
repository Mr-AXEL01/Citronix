package net.axel.citronix.controller;

import lombok.RequiredArgsConstructor;
import net.axel.citronix.service.HarvestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HarvestController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class HarvestController {

    public final static String CONTROLLER_PATH = "/api/v1/harvest";

    private final HarvestService service;
}
