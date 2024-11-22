package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;

public interface TreeService {

    TreeResponseDTO findById(Long id);

    TreeResponseDTO create(CreateTreeDTO dto);

}
