package com.api.tasteful.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receitas")
public class Receita {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="recipe_json",nullable=false)
	private String recipe_json;
	// ingredientes, modo de preparo, imagem, nome do usuario q postou
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecipe_json() {
		return recipe_json;
	}
	public void setRecipe_json(String recipe_json) {
		this.recipe_json = recipe_json;
	}
	
	
	

}
