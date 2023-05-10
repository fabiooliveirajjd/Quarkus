package com.fabio.projeto.controller;

import java.util.List;

import com.fabio.projeto.entidade.Produto;
import com.fabio.projeto.service.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@ApplicationScoped
@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    @POST
    @Transactional
    public Response criar(Produto produto) {
      produtoService.criar(produto);
      return Response.status(Response.Status.CREATED).entity(produto).build();
    }
  
    @GET
    public List<Produto> buscarTodos() {
      return produtoService.buscarTodos();
    }
   
    @GET
    @Path("/{id}")
    public Produto buscarPorId(@PathParam("id") Long id) {
      return produtoService.bucarPorid(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id") Long id, Produto produtoAtualizado) {
      produtoService.alterar(id, produtoAtualizado);
      return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id) {
      produtoService.deletar(id);
    }
}
