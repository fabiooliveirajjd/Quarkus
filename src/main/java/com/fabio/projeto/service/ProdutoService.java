package com.fabio.projeto.service;

import java.util.List;

import com.fabio.projeto.entidade.Produto;
import com.fabio.projeto.repository.ProdutoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProdutoService {
    
    @Inject
    ProdutoRepository produtoRepository;

    public void criar(Produto produto) {
        produtoRepository.persist(produto);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.listAll();
    }

    public Produto bucarPorid(Long id) {
        return produtoRepository.findById(id);
    }

    public void alterar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id);
        if (produtoExistente != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoRepository.persist(produtoExistente);
        } else {
            throw new EntityNotFoundException("Produto com id " + id + " não encontrado");
        }
    }
    
    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto != null) {
            produtoRepository.deleteById(id);
        } else {
            throw new NotFoundException("Produto não encontrado");
        }
    }

}
