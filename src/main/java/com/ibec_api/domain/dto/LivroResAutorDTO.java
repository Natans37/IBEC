package com.ibec_api.domain.dto;

import com.ibec_api.domain.persistence.entity.Categoria;
import com.ibec_api.domain.persistence.entity.Editora;

public record LivroResAutorDTO(
        String nome,
        String imagem1Url,
        String imagem2Url,
        String imagem3Url,
        String imagem4Url,
        String imagem5Url,
        Editora editora,
        String isbn10,
        String isbn13,
        String dimensoes,
        Integer paginas,
        Double preco,
        Boolean ativo,
        Integer estoque,
        Categoria categoria
) {
}
