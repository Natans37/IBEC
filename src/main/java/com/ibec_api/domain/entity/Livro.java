package com.ibec_api.domain.entity;

import com.ibec_api.domain.dto.LivroReqDTO;
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

    public Livro(LivroReqDTO livroReqDTO) {
        this.nome = livroReqDTO.nome();
        this.imagem1Url = livroReqDTO.imagem1Url();
        this.imagem2Url = livroReqDTO.imagem2Url();
        this.imagem3Url = livroReqDTO.imagem3Url();
        this.imagem4Url = livroReqDTO.imagem4Url();
        this.imagem5Url = livroReqDTO.imagem5Url();
        this.editora = livroReqDTO.editora();
        this.isbn10 = livroReqDTO.isbn10();
        this.isbn13 = livroReqDTO.isbn13();
        this.dimensoes = livroReqDTO.dimensoes();
        this.paginas = livroReqDTO.paginas();
        this.preco = livroReqDTO.preco();
        this.ativo = livroReqDTO.ativo();
        this.estoque = livroReqDTO.estoque();
        this.autor = livroReqDTO.autor();
    }
}
