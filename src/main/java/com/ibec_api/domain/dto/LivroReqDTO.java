package com.ibec_api.domain.dto;

import com.ibec_api.domain.entity.Autor;


public record LivroReqDTO(
        String nome,
        String imagem1Url,
        String imagem2Url,
        String imagem3Url,
        String imagem4Url,
        String imagem5Url,
        String editora,
        String isbn10,
        String isbn13,
        String dimensoes,
        Integer paginas,
        Double preco,
        Boolean ativo,
        Integer estoque,
        Autor autor
) {
}
