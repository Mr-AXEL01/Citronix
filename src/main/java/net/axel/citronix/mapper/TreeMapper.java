package net.axel.citronix.mapper;

import net.axel.citronix.domain.dtos.tree.CreateTreeDTO;
import net.axel.citronix.domain.dtos.tree.TreeResponseDTO;
import net.axel.citronix.domain.entities.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper extends BaseMapper<Tree, CreateTreeDTO, TreeResponseDTO> {

    @Mapping(target = "age", expression = "java(tree.getAge(tree.getPlantingDate()))")
    @Mapping(target = "productivity", expression = "java(tree.getProductivity(tree.getAge(tree.getPlantingDate())))")
    TreeResponseDTO toResponseDto(Tree tree);
}
