package com.ibec_api.web.controller;

import com.ibec_api.domain.dto.LivroReqDTO;
import com.ibec_api.domain.dto.LivroResDTO;
import com.ibec_api.domain.persistence.entity.Livro;
import com.ibec_api.domain.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public Page<LivroResDTO> obterTodosLivros(@PageableDefault(size = 10)Pageable paginacao){
        return livroService.obterTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody LivroReqDTO livroReqDTO, UriComponentsBuilder uriBuilder){
        Livro livro = livroService.criarLivro(livroReqDTO);
        URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable UUID id, @RequestBody LivroReqDTO livroReqDTO) {
        Livro livroAtualizado = livroService.atualizarLivro(id, livroReqDTO);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable UUID id) {
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }
}
