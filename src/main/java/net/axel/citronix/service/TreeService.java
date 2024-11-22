package net.axel.citronix.service;

import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;
import net.axel.citronix.domain.dtos.tree.UpdateTreeDTO;

import java.util.List;

public interface TreeService {

    TreeResponseDTO findById(Long id);

    List<TreeResponseDTO> findAll();

    TreeResponseDTO create(CreateTreeDTO dto);

    TreeResponseDTO update(Long id, UpdateTreeDTO dto);

    void delete(Long id);
}
