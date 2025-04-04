package com.ibec_api.domain.mappers;

import com.ibec_api.domain.dto.CategoriaReqDTO;
import com.ibec_api.domain.dto.CategoriaResDTO;
import com.ibec_api.domain.dto.CategoriaResumoDTO;
import com.ibec_api.domain.dto.LivroResDTO;
import com.ibec_api.domain.persistence.entity.Categoria;
import com.ibec_api.domain.persistence.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    @Mapping(target = "livros", source = "livros", qualifiedByName = "mapLivro")
    CategoriaResDTO toDto(Categoria categoria);

    CategoriaResumoDTO toResumoDto(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livros", ignore = true)
    Categoria toEntity(CategoriaReqDTO categoriaReqDTO);

    @Named("mapLivro")
    default LivroResDTO mapLivro(Livro livro) {
        if (livro == null) return null;
        return Mappers.getMapper(LivroMapper.class).toDto(livro);
    }
}
