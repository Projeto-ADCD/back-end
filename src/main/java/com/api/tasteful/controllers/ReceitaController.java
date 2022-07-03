package com.api.tasteful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.tasteful.entities.Receita;
import com.api.tasteful.services.ReceitaService;

@RestController
public class ReceitaController {
	
	@Autowired
	private ReceitaService receitaService;
	
	
	//falta ver direito as anotaçoes
	
	@GetMapping("receitas")
	public ResponseEntity<List<Receita>> getReceitas() {
		try {
			return new ResponseEntity<List<Receita>>(receitaService.getReceitas(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("receitas/{id}")
	public ResponseEntity<Receita> getReceita(@PathVariable int id) {
		try {
			return new ResponseEntity<Receita>(receitaService.getReceita(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	// @GetMapping("receitas/{}")
	public ResponseEntity<List<Receita>> filtrarReceitasPorIngrediente() {
		try {
			return new ResponseEntity<List<Receita>>(receitaService.filtrarReceitasPorIngredientes(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

}
