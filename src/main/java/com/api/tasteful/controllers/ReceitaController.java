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
	
	@GetMapping("/")
	public ResponseEntity<String> helloworld() {
		return new ResponseEntity<String>("hello world", HttpStatus.OK);
	}
	
	//falta ver direito as anotaçoes
	
	@GetMapping("/receitas")
	public ResponseEntity<List<Receita>> getReceitas() {
		return new ResponseEntity<List<Receita>>(receitaService.getReceitas(), HttpStatus.OK);
	}
	
	@GetMapping("/receitas/{id}")
	public ResponseEntity<Receita> getReceitaById(@PathVariable Integer id) {
		return new ResponseEntity<Receita>(receitaService.getReceitaById(id), HttpStatus.OK);
	}
	
	@GetMapping("/pesquisa/{contem_ingredientes}")
	public ResponseEntity<List<Receita>> filtrarReceitasPorIngrediente(@PathVariable String[] contem_ingredientes) {
		try {
			return new ResponseEntity<List<Receita>>(receitaService.filtrarReceitasPorIngredientes(contem_ingredientes), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	 
	@GetMapping("/pesquisa/{nao_contem_ingredientes}")
	public ResponseEntity<List<Receita>> filtrarReceitasSemIngrediente(@PathVariable String[] nao_contem_ingredientes) {
		try {
			return new ResponseEntity<List<Receita>>(receitaService.filtrarReceitasSemIngrediente(nao_contem_ingredientes), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
