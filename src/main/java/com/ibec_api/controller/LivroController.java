package com.ibec_api.controller;

import com.ibec_api.domain.dto.AutorReqDTO;
import com.ibec_api.domain.dto.LivroReqDTO;
import com.ibec_api.domain.entity.Autor;
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
import java.util.UUID;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public Page<Livro> obterTodosLivros(@PageableDefault(size = 10)Pageable paginacao){
        return livroService.obterTodos(paginacao);
    }

    @GetMapping("/editora")
    public Page<Livro> obterLivrosPorEditora(@PageableDefault(size = 10)Pageable paginacao, @RequestParam String editora) {
        return livroService.obterPorEditora(paginacao, editora);
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
