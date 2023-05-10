package com.fabio.projeto.controller;

import com.fabio.projeto.entidade.Cliente;
import com.fabio.projeto.service.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@ApplicationScoped
@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @POST
    @Transactional
    public Response criar(Cliente cliente) {
        clienteService.criar(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @GET
    public List<Cliente> buscarTodos() {
        return clienteService.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Cliente buscarPorid(@PathParam("id") Long id) {
        return clienteService.buscarPorid(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id") Long id, Cliente clienteAtualizado) {
        clienteService.alterar(id, clienteAtualizado);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
        clienteService.deletar(id);
    }

}