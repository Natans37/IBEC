package com.ibec_api.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioReqDTO(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        LocalDateTime dataNasc,
        String senha
        // List<EnderecoReqDTO> enderecos,
        // List<PagamentoReqDTO> metodosPagamento

) {
}
