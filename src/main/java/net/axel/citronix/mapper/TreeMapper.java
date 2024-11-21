package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;
import net.axel.citronix.domain.entities.Tree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreeMapper extends BaseMapper<Tree, CreateTreeDTO, TreeResponseDTO> {
}
