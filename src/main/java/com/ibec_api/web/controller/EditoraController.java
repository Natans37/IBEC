package com.ibec_api.web.controller;

import com.ibec_api.domain.dto.AutorReqDTO;
import com.ibec_api.domain.dto.AutorResDTO;
import com.ibec_api.domain.dto.EditoraReqDTO;
import com.ibec_api.domain.dto.EditoraResDTO;
import com.ibec_api.domain.persistence.entity.Autor;
import com.ibec_api.domain.persistence.entity.Editora;
import com.ibec_api.domain.service.AutorService;
import com.ibec_api.domain.service.EditoraService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/editoras")
@AllArgsConstructor
public class EditoraController {
    private EditoraService editoraService;

    @GetMapping
    public Page<EditoraResDTO> obterTodosAutores(@PageableDefault(size = 10) Pageable paginacao){
        return editoraService.obterTodos(paginacao);
    }

    @PostMapping
    public ResponseEntity<Editora> criarAutor(@RequestBody EditoraReqDTO editoraReqDTO, UriComponentsBuilder uriBuilder){
        Editora editora = editoraService.criarEditora(editoraReqDTO);
        URI uri = uriBuilder.path("/editora/{id}").buildAndExpand(editora.getId()).toUri();
        return ResponseEntity.created(uri).body(editora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> atualizarAutor(@PathVariable Long id, @RequestBody EditoraReqDTO editoraReqDTO) {
        Editora editoraAtualizado = editoraService.atualizarEditora(id, editoraReqDTO);
        return ResponseEntity.ok(editoraAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        editoraService.excluirEditora(id);
        return ResponseEntity.noContent().build();
    }
}
