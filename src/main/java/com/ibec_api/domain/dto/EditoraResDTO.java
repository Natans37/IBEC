package com.ibec_api.domain.dto;

import java.util.List;

public record EditoraResDTO(
        Long id,
        String nome,
        List<LivroResDTO> livros
) {
}
