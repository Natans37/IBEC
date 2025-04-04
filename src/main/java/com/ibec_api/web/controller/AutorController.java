package com.ibec_api.web.controller;

import com.ibec_api.domain.dto.AutorReqDTO;
import com.ibec_api.domain.dto.AutorResDTO;
import com.ibec_api.domain.persistence.entity.Autor;
import com.ibec_api.domain.service.AutorService;
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
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public Page<AutorResDTO> obterTodosAutores(@PageableDefault(size = 10) Pageable paginacao){
        return autorService.obterTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody AutorReqDTO autorReqDTO, UriComponentsBuilder uriBuilder){
        Autor autor = autorService.criarAutor(autorReqDTO);
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable UUID id, @RequestBody AutorReqDTO autorReqDTO) {
        Autor autorAtualizado = autorService.atualizarAutor(id, autorReqDTO);
        return ResponseEntity.ok(autorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable UUID id) {
        autorService.excluirAutor(id);
        return ResponseEntity.noContent().build();
    }
}
