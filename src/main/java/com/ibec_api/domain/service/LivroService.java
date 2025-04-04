package com.ibec_api.domain.service;

import com.ibec_api.domain.dto.*;
import com.ibec_api.domain.mappers.LivroMapper;
import com.ibec_api.domain.persistence.entity.Autor;
import com.ibec_api.domain.persistence.entity.Categoria;
import com.ibec_api.domain.persistence.entity.Editora;
import com.ibec_api.domain.persistence.entity.Livro;
import com.ibec_api.domain.persistence.repository.AutorRepository;
import com.ibec_api.domain.persistence.repository.CategoriaRepository;
import com.ibec_api.domain.persistence.repository.EditoraRepository;
import com.ibec_api.domain.persistence.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LivroService {
    private LivroRepository livroRepository;

    private AutorRepository autorRepository;

    private CategoriaRepository categoriaRepository;

    private EditoraRepository editoraRepository;

    private final LivroMapper livroMapper;


    public Page<LivroResDTO> obterTodos(Pageable paginacao) {
        return livroRepository.findAll(paginacao)
                .map(livroMapper::toDto);
    }

    public Livro criarLivro(LivroReqDTO livroReqDTO) {
        Autor autor = verificarOuCriarAutor(livroReqDTO.autor());
        Editora editora = verificarOuCriarEditora(livroReqDTO.editora());
        Categoria categoria = verificarOuCriarCategoria(livroReqDTO.categoria());

        Livro livro = new Livro(livroReqDTO);
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setCategoria(categoria);

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
        livro.setCategoria(livroReqDTO.categoria());

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

    private Autor verificarOuCriarAutor(Autor autor) {
        if (autor.getId() == null) {
            return autorRepository.save(autor);
        }
        return autorRepository.findById(autor.getId())
                .orElseGet(() -> autorRepository.save(autor));
    }

    private Editora verificarOuCriarEditora(Editora editora) {
        if (editora.getId() == null) {
            return editoraRepository.save(editora);
        }
        return editoraRepository.findById(editora.getId())
                .orElseGet(() -> editoraRepository.save(editora));
    }

    private Categoria verificarOuCriarCategoria(Categoria categoria) {
        if (categoria.getId() == null) {
            return categoriaRepository.save(categoria);
        }
        return categoriaRepository.findById(categoria.getId())
                .orElseGet(() -> categoriaRepository.save(categoria));
    }
}
