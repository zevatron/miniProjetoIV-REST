package com.herokuapp.filmerest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
			@FormParam("titulo")String titulo,
			@FormParam("diretor")String diretor,
			@FormParam("estudio")String estudio,
			@FormParam("genero")String genero,
			@FormParam("ano")String ano) {
		
		Filme filme = filmeRepository.findByTituloIgnoreCase(titulo);
		
		if(filme != null ) {
			return Response.status(Response.Status.CONFLICT).entity("JA EXISTE UM FILME COM O TITULO: " + titulo).build();
		}
		
		filme = new Filme(titulo, diretor, estudio, genero, ano);
		
		filmeRepository.save(filme);
		
		try {
			return Response.created(new URI("filmes?id=" + filme.getId().toString() + "&tipo=" + MediaType.APPLICATION_JSON)).build();
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
	@Produces(MediaType.TEXT_HTML)
	public Response encontrarPorTitulo(@PathParam("titulo") String titulo) {
		
		List<Filme> filmes = filmeRepository.findByTituloContainingIgnoreCase(titulo);
		if(filmes != null ) {
			return Response.ok(html(filmes), MediaType.TEXT_HTML).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("diretor/{diretor}")
	@Produces(MediaType.TEXT_HTML)
	public Response encontrarPorDiretor(@PathParam("diretor") String diretor) {
		
		List<Filme> filmes = filmeRepository.findByDiretorContainingIgnoreCase(diretor);
		if(filmes != null ) {
			return Response.ok(html(filmes), MediaType.TEXT_HTML).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("genero/{genero}")
	@Produces(MediaType.TEXT_HTML)
	public Response encontrarPorGenero(@PathParam("genero") String genero) {
		
		List<Filme> filmes = filmeRepository.findByGeneroContainingIgnoreCase(genero);
		if(filmes != null ) {
			return Response.ok(html(filmes), MediaType.TEXT_HTML).build();
		}
		return naoEncontrado;
	}
	
	@GET
	@Path("ano/{ano}")
	@Produces(MediaType.TEXT_HTML)
	public Response encontrarPorAno(@PathParam("ano") String ano) {
		
		List<Filme> filmes = filmeRepository.findByAnoContainingIgnoreCase(ano);
		if(filmes != null ) {
			return Response.ok(html(filmes), MediaType.TEXT_HTML).build();
		}
		return naoEncontrado;
	}
	
	private String html(List<Filme> filmes) {
		String urlRest = "http://localhost:8080/filmes";
		String html = "";
		for(Filme filme : filmes) {
			String str = "<tr>";	
			str += "<td>" + filme.getTitulo() + "</td>";
			str += "<td>" + filme.getDiretor() + "</td>";
			str += "<td>" + filme.getEstudio() + "</td>";
			str += "<td>" + filme.getGenero() + "</td>";
			str += "<td>" + filme.getAno() + "</td>";
			str += "<td>" + "<a href='edit.html?id="+filme.getId()+"'><i class='far fa-edit'></i></a>" + "</td>";
			str += "<td>" + "<a href='#' onclick='excluir("+filme.getId()+")'><i class='fas fa-trash'></i></a>" + "</td>";
			str += "<td>" + "<a href='"+urlRest+"?id="+filme.getId()+"&tipo=application/xml' >xml</a>" + "</td>";
			str += "<td>" + "<a href='"+urlRest+"?id="+filme.getId()+"&tipo=application/json' >json</a>" + "</td>";
			str += "</tr>";
			
			html+= str;
		}
		return html;
	}

}
