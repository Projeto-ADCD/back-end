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
	
	public List<Receita> filtrarReceitasPorIngredientes(String[] contem_ingredientes) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Receita> filtrarReceitasSemIngrediente(String[] nao_contem_ingredientes) {
		// TODO Auto-generated method stub
		return null;
	}

}
