package com.fabio.projeto.controller;

import com.fabio.projeto.entidade.Funcionario;
import com.fabio.projeto.service.FuncionarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioController {

    @Inject
    FuncionarioService funcionarioService;

    @POST
    @Transactional
    public Response criar (Funcionario funcionario){
        funcionarioService.criar(funcionario);
        return Response.status(Response.Status.CREATED).entity(funcionario).build();
    }

    @GET
    public List<Funcionario> buscarTodos(){
        return  funcionarioService.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Funcionario buscarPorId (@PathParam("id") Long id){
        return funcionarioService.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar (@PathParam("id") Long id, Funcionario funcionarioAtualizado){
        funcionarioService.alterar(id, funcionarioAtualizado);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id){
        funcionarioService.deletar(id);
    }

}
