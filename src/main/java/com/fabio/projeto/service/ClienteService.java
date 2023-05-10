package com.fabio.projeto.service;

import com.fabio.projeto.entidade.Cliente;

import com.fabio.projeto.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import  jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public void criar(Cliente cliente) {
        clienteRepository.persist(cliente);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.listAll();
    }

    public Cliente buscarPorid (Long id){
        return clienteRepository.findById(id);
    }

    @Transactional
    public void alterar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id);
        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpf(clienteAtualizado.getCpf());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteRepository.persist(clienteExistente);
        } else {
            throw new EntityNotFoundException("Cliente com id " + id + " não encontrado");
        }
    }

    @Transactional
    public void deletar(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente != null) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("Cliente não encontrado");
        }
    }
}
