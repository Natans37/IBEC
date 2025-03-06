package com.ibec_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioReqDTO(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        LocalDateTime dataNasc,
        @NotBlank
        String senha
        // List<EnderecoReqDTO> enderecos,
        // List<PagamentoReqDTO> metodosPagamento

) {
}
