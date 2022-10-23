package com.api.tasteful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.tasteful.entities.Receita;
import com.api.tasteful.services.ReceitaService;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Por enquanto só diz hello world!")
	})
	@GetMapping("/")
	public ResponseEntity<String> helloworld() {
		return new ResponseEntity<String>("hello world", HttpStatus.OK);
	}

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna uma lista com um subconjunto de receitas de acordo com a página")
	})
	@GetMapping("/receitas")
	public ResponseEntity<List<Receita>> getReceitas(@RequestParam(defaultValue="0") Integer page, 
				@RequestParam(defaultValue="10") Integer size,
				@RequestParam(defaultValue="") String tag) {
		return new ResponseEntity<List<Receita>>(receitaService.getReceitas(page, size, tag), HttpStatus.OK);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a receita correspondente ao ID único")
	})
	@GetMapping("/receitas/{id}")
	public ResponseEntity<Receita> getReceitaById(
			@ApiParam(
				    name =  "id",
//				    type = "Integer",
		    		value= "ID único de uma receita no banco de dados",
				    required = true)
			@PathVariable Integer id) {
		return new ResponseEntity<Receita>(receitaService.getReceitaById(id), HttpStatus.OK);
	}
	

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna as receitas paginadas ordenada pelo tempo em ordem crescente ou decrescente")
	})
	@GetMapping("/sort/tempo")
	public ResponseEntity<List<Receita>> sortByTempo(@RequestParam(defaultValue="0") Integer page, 
			@RequestParam(defaultValue="10") Integer size,
			@RequestParam(defaultValue="") String tag,
			@ApiParam(
				    name =  "ascending",
				    type = "boolean",
		    		value= "variavel booleana que dita se a ordenação será crescente ou não",
				    required = true)
			@RequestParam(defaultValue = "true") boolean ascending)  {
		return new ResponseEntity<List<Receita>>(receitaService.sortReceitasByTempo(page, size, ascending, tag), HttpStatus.OK);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna as receitas paginadas ordenada pela quantidade de porções em ordem crescente ou decrescente")
	})
	@GetMapping("/sort/porcao")
	public ResponseEntity<List<Receita>> sortByPorcao(@RequestParam(defaultValue="0") Integer page, 
			@RequestParam(defaultValue="10") Integer size,
			@RequestParam(defaultValue="") String tag,
			@ApiParam(
				    name =  "ascending",
				    type = "boolean",
		    		value= "variavel booleana que dita se a ordenação será crescente ou não",
				    required = true)
		@RequestParam(defaultValue = "true") boolean ascending)  {
		return new ResponseEntity<List<Receita>>(receitaService.sortReceitasByPorcao(page, size, ascending, tag), HttpStatus.OK);
	}
	

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna uma lista de Receitas que contenham a palavra chave (ou algo similar) no título.")
	})
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Receita>> getReceitaByNome(
			@ApiParam(
				    name =  "nomeReceita",
				    type = "String",
		    		value= "palavras chave que o usuário espera encontrar como título da receita",
				    required = false)
			@RequestParam(value = "nomeReceita", required = false) String nomeReceita,
			@RequestParam(defaultValue="0") Integer page, 
			@RequestParam(defaultValue="10") Integer size,
			@RequestParam(defaultValue = "") String paramOrdem,
			@RequestParam(defaultValue = "true") boolean ascending,
			@RequestParam(defaultValue = "") String tag)
	{ 
		return new ResponseEntity<List<Receita>>(receitaService.getReceitaByNomeGenerico(nomeReceita, page, size, paramOrdem, ascending, tag), HttpStatus.OK);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna uma lista de receitas que **obrigatoriamente** contém a lista de *ingredientes* e **não contém** a lista de *nao_ingredientes*.")
	})
	@GetMapping("/pesquisa/filtro")
	public ResponseEntity<List<Receita>> filtrarReceitas(
			@ApiParam(
				    name =  "ingredientes",
				    type = "String",
		    		value= "palavras chave que o usuário espera encontrar na lista de ingredientes da receita (embora esteja sendo buscado tanto no título quanto no corpo da receita também).",
				    required = false)
			@RequestParam(value = "ingredientes", required = false) String[] ingredientes,
			@ApiParam(
				    name =  "nao_ingredientes",
				    type = "String",
		    		value= "palavras chave que o usuário quer que *não* estejam presentes na receita.",
				    required = false)
			@RequestParam(value = "nao_ingredientes", required = false) String[] nao_ingredientes,			
			@RequestParam(defaultValue="0") Integer page, 
			@RequestParam(defaultValue="10") Integer size,
			@RequestParam(defaultValue = "") String paramOrdem,
			@RequestParam(defaultValue = "true") boolean ascending,
			@RequestParam(defaultValue = "") String tag)
	{ 
		return new ResponseEntity<List<Receita>>(receitaService.filtrarReceitasGenerico(ingredientes, nao_ingredientes, page, size, paramOrdem, ascending, tag), HttpStatus.OK);
	}
}
