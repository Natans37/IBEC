package com.ibec_api.domain.dto;

import jakarta.validation.constraints.NotBlank;


public record EditoraReqDTO(
        @NotBlank
        String nome
) {
}
