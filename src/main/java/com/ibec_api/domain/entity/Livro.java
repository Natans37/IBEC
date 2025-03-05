package com.ibec_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String editora;

    private String isbn10;

    private String isbn13;

    private String dimensoes;

    private Integer paginas;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
