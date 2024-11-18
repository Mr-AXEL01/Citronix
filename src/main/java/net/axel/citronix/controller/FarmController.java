package net.axel.citronix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FarmController.CONTROLLER_PATH)


public class FarmController {
    public final static String CONTROLLER_PATH = "/api/v1/farms";
}
