package com.ibec_api.domain.dto;

import java.util.List;
import java.util.UUID;

public record AutorResDTO(
        UUID id,
        String nome,
        String sobrenome,
        List<LivroResAutorDTO> livros
) {
}
