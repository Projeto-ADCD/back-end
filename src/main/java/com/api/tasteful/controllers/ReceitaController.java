package com.api.tasteful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/receitas")
	public ResponseEntity<List<Receita>> getReceitas() {
		return new ResponseEntity<List<Receita>>(receitaService.getReceitas(), HttpStatus.OK);
	}
	
	@GetMapping("/receitas/{id}")
	public ResponseEntity<Receita> getReceitaById(@PathVariable Integer id) {
		return new ResponseEntity<Receita>(receitaService.getReceitaById(id), HttpStatus.OK);
	}
	

	@GetMapping("/pesquisa/filtro")
	public ResponseEntity<List<Receita>> filtrarReceitas(@RequestParam(value = "ingredientes", required = false) String[] ingredientes, 
													@RequestParam(value = "nao_ingredientes", required = false) String[] nao_ingredientes) {
		return new ResponseEntity<List<Receita>>(receitaService.filtrarReceitas(ingredientes, nao_ingredientes), HttpStatus.OK);
	}
	 
//	@GetMapping("/pesquisa/{nao_contem_ingredientes}")
//	public ResponseEntity<List<String>> filtrarReceitasSemIngrediente(@PathVariable String[] nao_contem_ingredientes) {
//		return new ResponseEntity<List<String>>(receitaService.filtrarReceitasSemIngredientes(nao_contem_ingredientes), HttpStatus.OK);
//	}
}
