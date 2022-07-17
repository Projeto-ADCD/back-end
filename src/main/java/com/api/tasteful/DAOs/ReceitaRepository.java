package com.api.tasteful.DAOs;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.tasteful.entities.Receita;

@Repository
public interface ReceitaRepository<T, ID extends Serializable> extends JpaRepository<Receita, Integer> {
	
	@Query("select r from Receita as r where id=?1")
	Optional<Receita> getReceitaById(Integer id);
	
	@Query("SELECT recipe_json FROM Receita WHERE fts(:contem_ingredientes) = true")
	List<String> filtrarReceitasPorIngredientes(@Param("contem_ingredientes") String contem_ingredientes);
	
	@Query("SELECT recipe_json FROM Receita WHERE fts(:nao_contem_ingredientes) = true")
	List<String> filtrarReceitasSemIngredientes(@Param("nao_contem_ingredientes") String nao_contem_ingredientes);

}
