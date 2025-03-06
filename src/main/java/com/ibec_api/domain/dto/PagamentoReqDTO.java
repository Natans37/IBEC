package com.ibec_api.domain.dto;

public record PagamentoReqDTO(
        String nomeCartao,
        String sobrenomeCartao,
        Integer numeroCartao,
        Integer validade,
        Integer cvv
) {
}
