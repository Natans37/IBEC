package com.ibec_api.domain.dto;

public record EnderecoReqDTO(
        String cep,
        String rua,
        String cidade,
        String uf,
        Integer numero,
        String complemento
) {
}
