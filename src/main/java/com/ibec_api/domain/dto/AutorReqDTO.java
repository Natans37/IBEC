package com.ibec_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorReqDTO(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome
) {
}
