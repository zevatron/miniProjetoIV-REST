package com.herokuapp.filmerest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.herokuapp.filmerest.model.Filme;

@Repository("filmeRepository")
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	Filme findByTituloIgnoreCase(String titulo);
	List<Filme> findByTituloContainingIgnoreCase(String titulo);
	List<Filme> findByDiretorContainingIgnoreCase(String diretor);
	List<Filme> findByGeneroContainingIgnoreCase(String genero);
	List<Filme> findByAnoContainingIgnoreCase(String ano);

}
