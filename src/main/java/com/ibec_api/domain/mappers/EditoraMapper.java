package com.ibec_api.domain.mappers;

import com.ibec_api.domain.dto.EditoraReqDTO;
import com.ibec_api.domain.dto.EditoraResDTO;
import com.ibec_api.domain.dto.EditoraResumoDTO;
import com.ibec_api.domain.dto.LivroResDTO;
import com.ibec_api.domain.persistence.entity.Editora;
import com.ibec_api.domain.persistence.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EditoraMapper {
    @Mapping(target = "livros", source = "livros", qualifiedByName = "mapLivro")
    EditoraResDTO toDto(Editora editora);

    EditoraResumoDTO toResumoDto(Editora editora);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livros", ignore = true)
    Editora toEntity(EditoraReqDTO editoraReqDTO);

    @Named("mapLivro")
    default LivroResDTO mapLivro(Livro livro) {
        if (livro == null) return null;
        return Mappers.getMapper(LivroMapper.class).toDto(livro);
    }
}
