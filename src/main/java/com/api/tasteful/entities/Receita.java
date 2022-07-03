package com.api.tasteful.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Receita {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	// ingredientes, modo de preparo, imagem, nome do usuario q postou
	
}
