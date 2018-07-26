package com.herokuapp.filmerest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Filme {
	
	@Id
	@GeneratedValue
	private Long id;
	private String titulo, diretor, estudio, genero, ano;
	
	public Filme() {
		// TODO Auto-generated constructor stub
	}
	
	public Filme(Long id, String titulo, String diretor, String estudio, String genero, String ano) {
		this.id = id;
		this.titulo = titulo;
		this.diretor = diretor;
		this.estudio = estudio;
		this.genero = genero;
		this.ano = ano;
	}
	
	public Filme(String titulo, String diretor, String estudio, String genero, String ano) {
		this.titulo = titulo;
		this.diretor = diretor;
		this.estudio = estudio;
		this.genero = genero;
		this.ano = ano;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	

}
