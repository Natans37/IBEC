package com.ibec_api.domain.mappers;

import com.ibec_api.domain.dto.AutorReqDTO;
import com.ibec_api.domain.dto.AutorResDTO;
import com.ibec_api.domain.dto.AutorResumoDTO;
import com.ibec_api.domain.persistence.entity.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutorMapper {
    @Mapping(target = "livros", source = "livros")
    AutorResDTO toDto(Autor autor);

    AutorResumoDTO toResumoDto(Autor autor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livros", ignore = true)
    Autor toEntity(AutorReqDTO autorReqDTO);

}
