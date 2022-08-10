package com.api.tasteful.DAOs;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.tasteful.entities.Receita;

@Repository
public interface ReceitaRepository<T, ID extends Serializable> extends JpaRepository<Receita, Integer> {

	@Query("SELECT r FROM Receita AS r WHERE id=?1")
	Optional<Receita> getReceitaById(Integer id);

	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true")
	Page<Receita> filtrarReceitas(@Param("ingredientes") String ingredientes, Pageable pageable);

	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true")
	Page<Receita> getReceitaByNome(@Param("nomeReceita") String nomeReceita, Pageable pageable);
}