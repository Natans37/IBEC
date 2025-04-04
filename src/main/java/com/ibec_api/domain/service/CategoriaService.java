package com.ibec_api.domain.service;

import com.ibec_api.domain.dto.CategoriaReqDTO;
import com.ibec_api.domain.dto.CategoriaResDTO;
import com.ibec_api.domain.mappers.CategoriaMapper;
import com.ibec_api.domain.persistence.entity.Categoria;
import com.ibec_api.domain.persistence.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    private final CategoriaMapper categoriaMapper;

    public Page<CategoriaResDTO> obterTodos(Pageable paginacao) {
        return categoriaRepository.findAll(paginacao)
                .map(categoriaMapper::toDto);
    }

    public Categoria criarEditora(CategoriaReqDTO categoriaReqDTO) {
        Categoria categoria;
        Optional<Categoria> categoriaExistente = categoriaRepository.findByNome(categoriaReqDTO.nome());
        if (categoriaExistente.isPresent()) {
            throw new RuntimeException("Categoria já existente com o nome: " + categoriaExistente.get().getNome());
        } else {
            categoria = new Categoria(categoriaReqDTO);
            categoriaRepository.save(categoria);
        }
        return categoria;
    }

    public Categoria atualizarEditora(Long id, CategoriaReqDTO categoriaReqDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com o ID: " + id));

        categoria.setNome(categoriaReqDTO.nome());

        return categoriaRepository.save(categoria);
    }

    public void excluirEditora(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Editora não encontrada com o ID:" + id);
        }

        categoriaRepository.deleteById(id);
    }
}
