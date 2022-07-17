package com.api.tasteful.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.tasteful.DAOs.ReceitaRepository;
import com.api.tasteful.entities.Receita;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository<Receita, Integer> receitaRepository;


	public List<Receita> getReceitas() {
		return receitaRepository.findAll();
	}

	public Receita getReceitaById(Integer id) {
		return receitaRepository.getReceitaById(id).get();
	}
	
	public List<String> filtrarReceitasPorIngredientes(String[] contem_ingredientes) {
		String ingredientes = "";
		for (int i=0; i < contem_ingredientes.length - 1; i++) {
			ingredientes += contem_ingredientes[i] + " & ";
		}
		
		ingredientes += contem_ingredientes[-1];
			
		return receitaRepository.filtrarReceitasPorIngredientes(ingredientes);
	}
	

	public List<String> filtrarReceitasSemIngredientes(String[] nao_contem_ingredientes) {
		//return receitaRepository.filtrarReceitasSemIngredientes(nao_contem_ingredientes);
		return null;
	}

}
