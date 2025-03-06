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

    private String imagem1Url;

    private String imagem2Url;

    private String imagem3Url;

    private String imagem4Url;

    private String imagem5Url;

    private String editora;

    private String isbn10;

    private String isbn13;

    private String dimensoes;

    private Integer paginas;

    private Double preco;

    private Boolean ativo;

    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
