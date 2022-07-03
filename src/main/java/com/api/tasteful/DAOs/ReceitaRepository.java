package com.api.tasteful.DAOs;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tasteful.entities.Receita;

public interface ReceitaRepository<T, ID extends Serializable> extends JpaRepository<Receita, Long> {

	// @Query("select ")
	List<Receita> findAll();
	
	Optional<Receita> getReceitaById(Long id);
	
	
}
