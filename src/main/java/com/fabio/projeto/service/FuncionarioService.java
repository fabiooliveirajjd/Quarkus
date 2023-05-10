package com.fabio.projeto.service;

import com.fabio.projeto.entidade.Funcionario;
import com.fabio.projeto.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class FuncionarioService {

    @Inject
    FuncionarioRepository funcionarioRepository;

    public void criar(Funcionario funcionario){
        funcionarioRepository.persist(funcionario);
    }
    public List<Funcionario> buscarTodos(){
      return funcionarioRepository.listAll();
    }

    public Funcionario buscarPorId(Long id){
        return funcionarioRepository.findById(id);
    }

    @Transactional
    public void alterar (Long id, Funcionario funcionarioAtualizado){
        Funcionario funcionarioExistente = funcionarioRepository.findById(id);
        if (funcionarioExistente != null){
            funcionarioExistente.setNome(funcionarioAtualizado.getNome());
            funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
            funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
            funcionarioRepository.persist(funcionarioExistente);
        }else{
            throw new EntityNotFoundException("Funcionario com id " + id + " não encontrado");
        }
    }

    @Transactional
    public  void deletar (Long id){
       Funcionario funcionario = funcionarioRepository.findById(id);
       if (funcionario != null){
           funcionarioRepository.deleteById(id);
       }else {
           throw new NotFoundException("Funcionário não encontrado");
       }
    }
}
