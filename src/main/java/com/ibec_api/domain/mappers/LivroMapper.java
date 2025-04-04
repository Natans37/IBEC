package com.ibec_api.domain.mappers;

import com.ibec_api.domain.dto.LivroResDTO;
import com.ibec_api.domain.persistence.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AutorMapper.class, EditoraMapper.class, CategoriaMapper.class})
public interface LivroMapper {

    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "editora", source = "editora")
    @Mapping(target = "autor", source = "autor")
    LivroResDTO toDto(Livro livro);
}
