package com.ibec_api.service;

import com.ibec_api.domain.dto.LivroReqDTO;
import com.ibec_api.domain.entity.Livro;
import com.ibec_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro criarLivro(LivroReqDTO livroReqDTO){
        Livro livro = new Livro(livroReqDTO);
        livroRepository.save(livro);
        return livro;
    }

    public Page<Livro> obterTodos(Pageable paginacao){
       return livroRepository.findAll(paginacao);
    }
}
