package com.ibec_api.service;

import com.ibec_api.domain.dto.AutorReqDTO;
import com.ibec_api.domain.dto.AutorResDTO;
import com.ibec_api.domain.dto.LivroResAutorDTO;
import com.ibec_api.domain.entity.Autor;
import com.ibec_api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Page<AutorResDTO> obterTodos(Pageable paginacao) {
        Page<Autor> autores = autorRepository.findAll(paginacao);
        return autores.map(autor -> new AutorResDTO(
                autor.getId(),
                autor.getNome(),
                autor.getSobrenome(),
                autor.getLivros().stream()
                        .map(livro -> new LivroResAutorDTO(
                                livro.getNome(),
                                livro.getImagem1Url(),
                                livro.getImagem2Url(),
                                livro.getImagem3Url(),
                                livro.getImagem4Url(),
                                livro.getImagem5Url(),
                                livro.getEditora(),
                                livro.getIsbn10(),
                                livro.getIsbn13(),
                                livro.getDimensoes(),
                                livro.getPaginas(),
                                livro.getPreco(),
                                livro.getAtivo(),
                                livro.getEstoque()
                        ))
                        .toList()
        ));
    }

    public Autor criarAutor(AutorReqDTO autorReqDTO){
        Autor autor;
        Optional<Autor> autorExistente = autorRepository.findByNomeAndSobrenome(autorReqDTO.nome(), autorReqDTO.sobrenome());
        if(autorExistente.isPresent()){
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

    public void excluirAutor(UUID id){

        if(!autorRepository.existsById(id)){
            throw  new RuntimeException("Autor não encontrado com o ID:" + id);
        }

        autorRepository.deleteById(id);
    }
}
