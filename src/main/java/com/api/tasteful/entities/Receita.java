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
	@Column(name="recipe_json", nullable=false)
	private String recipe_json;
	@Column(name="has_image")
	private boolean has_image;
	@Column(name="directory_name")
	private String directory_name;
	
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

	public boolean isHas_image() {
		return has_image;
	}

	public void setHas_image(boolean has_image) {
		this.has_image = has_image;
	}

	public String getDirectory_name() {
		return directory_name;
	}

	public void setDirectory_name(String directory_name) {
		this.directory_name = directory_name;
	}
	
}
