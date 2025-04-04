package com.ibec_api.domain.service;

import com.ibec_api.domain.dto.*;
import com.ibec_api.domain.mappers.EditoraMapper;
import com.ibec_api.domain.persistence.entity.Editora;
import com.ibec_api.domain.persistence.repository.EditoraRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditoraService {
    private EditoraRepository editoraRepository;

    private final EditoraMapper editoraMapper;

    public Page<EditoraResDTO> obterTodos(Pageable paginacao) {
        return editoraRepository.findAll(paginacao)
                .map(editoraMapper::toDto);
    }

    public Editora criarEditora(EditoraReqDTO editoraReqDTO) {
        Editora editora;
        Optional<Editora> editoraExistente = editoraRepository.findByNome(editoraReqDTO.nome());
        if (editoraExistente.isPresent()) {
            throw new RuntimeException("Editora já existente com o nome: " + editoraExistente.get().getNome());
        } else {
            editora = new Editora(editoraReqDTO);
            editoraRepository.save(editora);
        }
        return editora;
    }

    public Editora atualizarEditora(Long id, EditoraReqDTO editoraReqDTO) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com o ID: " + id));

        editora.setNome(editoraReqDTO.nome());

        return editoraRepository.save(editora);
    }

    public void excluirEditora(Long id) {

        if (!editoraRepository.existsById(id)) {
            throw new RuntimeException("Editora não encontrada com o ID:" + id);
        }

        editoraRepository.deleteById(id);
    }
}
