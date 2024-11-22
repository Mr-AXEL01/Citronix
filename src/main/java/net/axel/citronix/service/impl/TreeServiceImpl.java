package net.axel.citronix.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.mapper.FieldMapper;
import net.axel.citronix.mapper.TreeMapper;
import net.axel.citronix.repository.TreeRepository;
import net.axel.citronix.service.FieldService;
import net.axel.citronix.service.TreeService;
import org.springframework.stereotype.Service;

@Service
@Transactional

@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository repository;
    private final TreeMapper mapper;
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;


}
