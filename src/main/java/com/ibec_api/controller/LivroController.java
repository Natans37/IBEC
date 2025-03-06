package com.ibec_api.controller;

import com.ibec_api.domain.dto.LivroReqDTO;
import com.ibec_api.domain.entity.Livro;
import com.ibec_api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public Page<Livro> obterTodos(@PageableDefault(size = 10)Pageable paginacao){
        return livroService.obterTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<Livro> criarCliente(@RequestBody LivroReqDTO livroReqDTO, UriComponentsBuilder uriBuilder){
        Livro livro = livroService.criarLivro(livroReqDTO);
        URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }
}
