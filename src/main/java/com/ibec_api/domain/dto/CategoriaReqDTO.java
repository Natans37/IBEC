package com.ibec_api.domain.dto;

import jakarta.validation.constraints.NotBlank;


public record CategoriaReqDTO(
        @NotBlank
        String nome,
        String descricao
) {
}
