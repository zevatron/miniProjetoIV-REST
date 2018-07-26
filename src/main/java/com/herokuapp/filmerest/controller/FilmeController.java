package com.herokuapp.filmerest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.herokuapp.filmerest.model.Filme;
import com.herokuapp.filmerest.repository.FilmeRepository;

//String titulo, diretor, estudio, genero, ano
@Component
@Path("filmes")
public class FilmeController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	private Response naoEncontrado = Response.status(Response.Status.NOT_FOUND).entity("RECURSO NAO ENCONTRADO").build();

//	private List<Filme> filmes;
	
	@POST
	public Response cadastrar(
			@QueryParam("titulo")String titulo,
			@QueryParam("diretor")String diretor,
			@QueryParam("estudio")String estudio,
			@QueryParam("genero")String genero,
			@QueryParam("ano")String ano) {
		
		List<Filme>filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
		
		if(filmes != null ) {
			return Response.status(Response.Status.CONFLICT).entity("JA EXISTE UM FILME COM O TITULO: " + titulo).build();
		}
		
		Filme f = new Filme(titulo, diretor, estudio, genero, ano);
		
		filmeRepository.save(f);
		
		try {
			return Response.created(new URI("filmes?id=" + f.getId().toString())).build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Path("{id}/{titulo}")
	public Response atualizar(@PathParam("id") Long id , @PathParam("titulo") String titulo) {
		
		Filme f = filmeRepository.findById(id).orElse(null);
		
		if(f != null) {
			f.setTitulo(titulo);
			filmeRepository.save(f);
			return Response.ok(f,MediaType.APPLICATION_JSON).build();
		}
		
		return naoEncontrado;
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response apagar(@PathParam("id") Long id) {
		Filme f = filmeRepository.findById(id).orElse(null);
		if (f != null) {
			filmeRepository.delete(f);
			return Response.ok(f, MediaType.APPLICATION_XML).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("listar")
	public Response listar() {
		return Response.ok(filmeRepository.findAll(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})	
	public Response encontrar(@QueryParam("id") Long id , @QueryParam("tipo") String tipo) {
		
		Filme filme = filmeRepository.findById(id).orElse(null);
		if(filme != null) {
			return Response.ok(filme,MediaType.valueOf(tipo)).build();			
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("titulo/{titulo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrarPorTitulo(@PathParam("titulo") String titulo) {
		
		List<Filme> filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
		if(filmes != null ) {
			return Response.ok(filmes, MediaType.APPLICATION_JSON).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("diretor/{diretor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrarPorDiretor(@PathParam("diretor") String diretor) {
		
		List<Filme> filmes = filmeRepository.findByDiretorContainingIgnoreCase(diretor);
		if(filmes != null ) {
			return Response.ok(filmes, MediaType.APPLICATION_JSON).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrarPorGenero(@PathParam("genero") String genero) {
		
		List<Filme> filmes = filmeRepository.findByGeneroContainingIgnoreCase(genero);
		if(filmes != null ) {
			return Response.ok(filmes, MediaType.APPLICATION_JSON).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("ano/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrarPorAno(@PathParam("ano") String ano) {
		
		List<Filme> filmes = filmeRepository.findByAnoContainingIgnoreCase(ano);
		if(filmes != null ) {
			return Response.ok(filmes, MediaType.APPLICATION_JSON).build();
		}
		return naoEncontrado;
	}

}