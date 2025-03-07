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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Page<Livro> obterTodos(Pageable paginacao){
        return livroRepository.findAll(paginacao);
    }

    public Page<Livro> obterPorEditora(Pageable paginacao, String editora){
        return livroRepository.findByEditora(paginacao, editora);
    }

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

    public Livro atualizarLivro(UUID id, LivroReqDTO livroReqDTO) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + id));

        livro.setNome(livroReqDTO.nome());
        livro.setImagem1Url(livroReqDTO.imagem1Url());
        livro.setImagem2Url(livroReqDTO.imagem2Url());
        livro.setImagem3Url(livroReqDTO.imagem3Url());
        livro.setImagem4Url(livroReqDTO.imagem4Url());
        livro.setImagem5Url(livroReqDTO.imagem5Url());
        livro.setEditora(livroReqDTO.editora());
        livro.setIsbn10(livroReqDTO.isbn10());
        livro.setIsbn13(livroReqDTO.isbn13());
        livro.setDimensoes(livroReqDTO.dimensoes());
        livro.setPaginas(livroReqDTO.paginas());
        livro.setPreco(livroReqDTO.preco());
        livro.setAtivo(livroReqDTO.ativo());
        livro.setEstoque(livroReqDTO.estoque());

        // atualizar o autor (se necessário)
        if (livroReqDTO.autor() != null) {
            Autor autor = autorRepository.findById(livroReqDTO.autor().getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + livroReqDTO.autor().getId()));
            livro.setAutor(autor);
        }

        return livroRepository.save(livro);
    }

    public void excluirLivro(UUID id){

        if(!livroRepository.existsById(id)){
            throw  new RuntimeException("Autor não encontrado com o ID:" + id);
        }

        livroRepository.deleteById(id);
    }
}
