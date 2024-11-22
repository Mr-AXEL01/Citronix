package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;

import java.util.List;

public interface TreeService {

    TreeResponseDTO findById(Long id);

    List<TreeResponseDTO> findAll();

    TreeResponseDTO create(CreateTreeDTO dto);

}
