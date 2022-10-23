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

	// pega receita por id unico
	@Query("SELECT r FROM Receita AS r WHERE id=?1")
	Optional<Receita> getReceitaById(Integer id);
	
	// filtro comum de receitas
	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true")
	Page<Receita> filtrarReceitas(@Param("ingredientes") String ingredientes, Pageable pageable);
	
	// filtrar receitas por tempo ##	
	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true ORDER BY tempo ASC")
	Page<Receita> filtrarReceitasSortedTempoASC(@Param("ingredientes") String ingredientes, Pageable pageable);
	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true ORDER BY tempo DESC")
	Page<Receita> filtrarReceitasSortedTempoDESC(@Param("ingredientes") String ingredientes, Pageable pageable);
	
	// filtrar receitas por porção
	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true ORDER BY porcoes ASC")
	Page<Receita> filtrarReceitasSortedPorcaoASC(@Param("ingredientes") String ingredientes, Pageable pageable);
	@Query("SELECT r FROM Receita AS r WHERE query_recipe(:ingredientes) = true ORDER BY porcoes DESC")
	Page<Receita> filtrarReceitasSortedPorcaoDESC(@Param("ingredientes") String ingredientes, Pageable pageable);
	
	// filtrar receitas por chave do nome
	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true")
	Page<Receita> getReceitaByNome(@Param("nomeReceita") String nomeReceita, Pageable pageable);
	
	// filtrar receitas por chave do nome e por tempo
	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true ORDER BY tempo ASC")
	Page<Receita> getReceitaByNomeTempoASC(@Param("nomeReceita") String nomeReceita, Pageable pageable);
	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true ORDER BY tempo DESC")
	Page<Receita> getReceitaByNomeTempoDESC(@Param("nomeReceita") String nomeReceita, Pageable pageable);

	// filtrar receitas por chave do nome e por porcao
	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true ORDER BY porcoes ASC")
	Page<Receita> getReceitaByNomePorcaoASC(@Param("nomeReceita") String nomeReceita, Pageable pageable);
	@Query("SELECT r FROM Receita AS r WHERE query_name(:nomeReceita) = true ORDER BY porcoes DESC")
	Page<Receita> getReceitaByNomePorcaoDESC(@Param("nomeReceita") String nomeReceita, Pageable pageable);
	
	
	// ordenação de receitas por tempo
	@Query("SELECT r FROM Receita AS r  WHERE tempo > 1 ORDER BY tempo ASC")
	Page<Receita> sortReceitasByTempoASC(Pageable pageable);
	@Query("SELECT r FROM Receita AS r ORDER BY tempo DESC")
	Page<Receita> sortReceitasByTempoDESC(Pageable pageable);
	
	@Query("SELECT r FROM Receita AS r  WHERE tempo > 1 AND query_tag(:tag) = true ORDER BY tempo ASC")
	Page<Receita> sortReceitasByTempoTagASC(@Param("tag") String tag, Pageable pageable);
	@Query("SELECT r FROM Receita AS r ORDER BY tempo DESC")
	Page<Receita> sortReceitasByTempoTagDESC(@Param("tag") String tag, Pageable pageable);

	
	// ordenação de receitas por porção
	@Query("SELECT r FROM Receita AS r WHERE porcoes > 0 ORDER BY porcoes ASC")
	Page<Receita> sortReceitasByPorcaoASC(Pageable pageable);
	@Query("SELECT r FROM Receita AS r ORDER BY porcoes DESC")
	Page<Receita> sortReceitasByPorcaoDESC(Pageable pageable);
	
	@Query("SELECT r FROM Receita AS r  WHERE porcoes > 0 AND query_tag(:tag) = true ORDER BY porcoes ASC")
	Page<Receita> sortReceitasByPorcaoTagASC(@Param("tag") String tag, Pageable pageable);
	@Query("SELECT r FROM Receita AS r ORDER BY porcoes DESC")
	Page<Receita> sortReceitasByPorcaoTagDESC(@Param("tag") String tag, Pageable pageable);
}

















