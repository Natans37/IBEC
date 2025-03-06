package com.ibec_api.service;

import com.ibec_api.domain.dto.LivroReqDTO;
import com.ibec_api.domain.entity.Autor;
import com.ibec_api.domain.entity.Livro;
import com.ibec_api.repository.AutorRepository;
import com.ibec_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro criarLivro(LivroReqDTO livroReqDTO) {
        Autor autor;

        // verifica se o autorId foi fornecido no DTO
        if (livroReqDTO.autor().getId() == null) {
           // se nao foi fornecido cria um novo autor
            autor = new Autor();
            autor.setNome(livroReqDTO.autor().getNome());
            autor.setSobrenome(livroReqDTO.autor().getSobrenome());
            autor = autorRepository.save(autor);
        } else {
            // se o autorId foi fornecido, verifica se o autor já existe no db
            UUID autorId = livroReqDTO.autor().getId();
            Optional<Autor> autorExistente = autorRepository.findById(autorId);

            if (autorExistente.isPresent()) {
                // se o autor existe, usa o autor existente
                autor = autorExistente.get();
            } else {
                // se o autor não existe, cria um novo autor com o ID fornecido
                autor = new Autor();
                autor.setNome(livroReqDTO.autor().getNome());
                autor.setSobrenome(livroReqDTO.autor().getSobrenome());
                autor = autorRepository.save(autor); // salva o autor
            }
        }

        // cria o livro e associa o autor
        Livro livro = new Livro(livroReqDTO);
        livro.setAutor(autor); // associa o autor ao livro

        // salva o livro no banco de dados
        return livroRepository.save(livro);
    }

    public Page<Livro> obterTodos(Pageable paginacao){
       return livroRepository.findAll(paginacao);
    }
}
