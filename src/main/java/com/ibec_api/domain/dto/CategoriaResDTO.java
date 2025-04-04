package com.ibec_api.domain.dto;

import java.util.List;

public record CategoriaResDTO(
        Long id,
        String nome,
        String descricao,
        List<LivroResDTO>livros
) {
}
