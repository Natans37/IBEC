package com.ibec_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private LocalDateTime dataNasc;

    private String senha;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Pagamento metodoPagamento;

}
