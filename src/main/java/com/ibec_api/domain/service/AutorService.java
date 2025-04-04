package com.ibec_api.domain.service;

import com.ibec_api.domain.dto.*;
import com.ibec_api.domain.mappers.AutorMapper;
import com.ibec_api.domain.persistence.entity.Autor;
import com.ibec_api.domain.persistence.repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AutorService {
    private AutorRepository autorRepository;

    private final AutorMapper autorMapper;

    public Page<AutorResDTO> obterTodos(Pageable paginacao) {
        return autorRepository.findAll(paginacao)
                .map(autorMapper::toDto);
    }

    public Autor criarAutor(AutorReqDTO autorReqDTO) {
        Autor autor;
        Optional<Autor> autorExistente = autorRepository.findByNomeAndSobrenome(autorReqDTO.nome(), autorReqDTO.sobrenome());
        if (autorExistente.isPresent()) {
            throw new RuntimeException("Autor já existente com o nome: " + autorExistente.get().getNome() + " " + autorExistente.get().getSobrenome());
        } else {
            autor = new Autor(autorReqDTO);
            autorRepository.save(autor);
        }
        return autor;
    }

    public Autor atualizarAutor(UUID id, AutorReqDTO autorReqDTO) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + id));

        autor.setNome(autorReqDTO.nome());
        autor.setSobrenome(autorReqDTO.sobrenome());

        return autorRepository.save(autor);
    }

    public void excluirAutor(UUID id) {

        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com o ID:" + id);
        }

        autorRepository.deleteById(id);
    }
}
