package net.axel.citronix.controller;

import lombok.RequiredArgsConstructor;
import net.axel.citronix.service.TreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TreeController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class TreeController {

    public final static String CONTROLLER_PATH = "/api/v1/trees";

    private final TreeService service;
}
