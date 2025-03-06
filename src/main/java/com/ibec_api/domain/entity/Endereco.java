package com.ibec_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String rua;

    private String cidade;

    private String uf;

    private Integer numero;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
