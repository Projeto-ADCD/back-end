package com.api.tasteful.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tasteful.DAOs.ReceitaRepository;
import com.api.tasteful.entities.Receita;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository<Receita, Integer> receitaRepository;


	public List<Receita> getReceitas() {
		// TODO Auto-generated method stub
//		return receitaRepository.prkt();
		return receitaRepository.findAll();
	}

	public Receita getReceitaById(Integer id) {
		return receitaRepository.getReceitaById(id).get();
	}
	
	public Receita getReceita() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public List<JSONObject> filtrarReceitasPorIngredientes(String contem_ingredientes) {
//		List<String> lista_receitas = receitaRepository.filtrarReceitasPorIngredientes(contem_ingredientes).get();
//		List<JSONObject> recipes_json = new ArrayList();
//		for (int i = 0; i < lista_receitas.size(); i++) {
//			recipes_json.add(recipe_toJson(lista_receitas.get(i)));
//		}
//		return recipes_json;
//	}
	
	public List<String> filtrarReceitasPorIngredientes(String contem_ingredientes) {
		List<String> lista = receitaRepository.filtrarReceitasPorIngredientes(contem_ingredientes).get();
		List<String> format_lista = new ArrayList<>();
		String pattern = "[(\\)]";
		String formatted = "";
		
		for (int i = 0; i < lista.size(); i++) {
			formatted = lista.get(i).replaceAll(pattern, "");
			format_lista.add(formatted);
		}
		
		return format_lista;
	}
//	public List<Map<String, String>> filtrarReceitasPorIngredientes(String contem_ingredientes) {
//		return receitaRepository.filtrarReceitasPorIngredientes(contem_ingredientes).get();
//	}
	

	public List<String> filtrarReceitasSemIngrediente(String[] nao_contem_ingredientes) {
		// TODO Auto-generated method stub
		return null;
	}

	private JSONObject recipe_toJson(String recipe) {
		return new JSONObject(recipe);
	}
}
