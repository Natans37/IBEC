package com.ibec_api.domain.dto;

import java.util.UUID;

public record AutorResumoDTO(
        UUID id,
        String nome,
        String sobrenome
) {
}
