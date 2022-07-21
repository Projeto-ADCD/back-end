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
	
	public List<Receita> filtrarReceitas(String[] contem_ingredientes, String[] nao_contem_ingredientes) {
		String ingredientes = filtrarReceitasPorIngredientes(contem_ingredientes);
		String nao_ingredientes = filtrarReceitasSemIngredientes(nao_contem_ingredientes);
		
		String filtro = ingredientes;
		if (filtro == "")
			filtro = nao_ingredientes;
		else if (nao_ingredientes != ""){
			filtro += " & " + nao_ingredientes;
		}
		
		System.out.println(filtro);
		return receitaRepository.filtrarReceitas(filtro);
	}
	
	private String filtrarReceitasPorIngredientes(String[] contem_ingredientes) {
		String ingredientes = "";
		
		if (contem_ingredientes != null && contem_ingredientes.length > 0) {
			int tamanho = contem_ingredientes.length;
			for (int i=0; i < tamanho - 1; i++) {
				ingredientes += contem_ingredientes[i].replaceAll(" ", " <-> ") + " & ";
			}
			
			ingredientes += contem_ingredientes[tamanho - 1].replaceAll(" ", " <-> ");
		}
		
		return ingredientes;
	}
	

	private String filtrarReceitasSemIngredientes(String[] nao_contem_ingredientes) {
		String ingredientes = "";
		
		if (nao_contem_ingredientes != null && nao_contem_ingredientes.length > 0) {
			int tamanho = nao_contem_ingredientes.length;
			for (int i=0; i < tamanho - 1; i++) {
				ingredientes += "!(" + nao_contem_ingredientes[i].replaceAll(" ", " <-> ") + ") & ";
			}
			
			ingredientes += "!(" + nao_contem_ingredientes[tamanho - 1].replaceAll(" ", " <-> ") + ")";
		}
		
		return ingredientes;
	}

	public List<Receita> getReceitaByNome(String nomeReceita) {
		return receitaRepository.getReceitaByNome(nomeReceita).get();
	}

}
