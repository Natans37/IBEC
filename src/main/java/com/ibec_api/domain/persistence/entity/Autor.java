package com.ibec_api.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibec_api.domain.dto.AutorReqDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String sobrenome;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Livro> livros;

    public Autor(AutorReqDTO autorReqDTO) {
        this.nome = autorReqDTO.nome();
        this.sobrenome = autorReqDTO.sobrenome();
    }
}
