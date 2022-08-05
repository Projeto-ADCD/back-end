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

	
	/**
	 * Simplesmente retorna uma lista com todas as receitas existentes no banco de dados
	 * @return Lista com todas as receitas
	 */
	public List<Receita> getReceitas() {
		return receitaRepository.findAll();
	}

	/**
	 * Retorna a receita correspondente ao id do banco de dados
	 * 
	 * @param id inteiro, chave primária do banco de dados
	 * @return A receita com o id correto
	 */
	public Receita getReceitaById(Integer id) {
		return receitaRepository.getReceitaById(id).get();
	}
	
	/**
	 * Deve retornar uma lista de receitas que contém os ingredientes da primeira lista e absolutamente não pode conter nenhum ingrediente da segunda lista.
	 * @param contem_ingredientes lista de ingredientes que o usuário deseja ter na receita
	 * @param nao_contem_ingredientes lista de ingredientes que não podem estar presentes na receita
	 * @return Uma lista de receitas de acordo com os ingredientes que devem ter (lista 1) e os ingredientes que não devem ter (lista 2)
	 */
	public List<Receita> filtrarReceitas(String[] contem_ingredientes, String[] nao_contem_ingredientes) {
		String ingredientes = filtrarReceitasPorIngredientes(contem_ingredientes);
		String nao_ingredientes = filtrarReceitasSemIngredientes(nao_contem_ingredientes);
		
		String filtro = ingredientes;
		
		if (filtro == "") {
			filtro = nao_ingredientes;
		} else if (nao_ingredientes != ""){
			filtro += " & " + nao_ingredientes;
		}
		
		System.out.println(filtro);
		
		return receitaRepository.filtrarReceitas(filtro);
	}
	
	/**
	 * Função auxiliar para transfomar a lista de ingredientes no formato de query que o SQL espera receber
	 * @param contem_ingredientes lista de string com os ingredientes que precisam ter na receita
	 * @return Uma string no formato de query do SQL com todos os ingredientes da busca
	 */
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
	
	
	/**
	 * Função auxiliar para transfomar a lista de **nao_ingredientes** no formato de query que o SQL espera receber
	 * @param nao_contem_ingredientes lista de string com os ingredientes que não podem estar na receita
	 * @return Uma string no formato de query do SQL com todos os ingredientes da busca
	 */
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

	/**
	 * Busca receitas no banco de dados que contenham a palavra chave recebida no título da receita
	 * @param nomeReceita Palavra-chave para fazer a busca por "nome de receita"
	 * @return Uma lista com todas as receitas que correspondam à palavra chave inserida
	 */
	public List<Receita> getReceitaByNome(String nomeReceita) {
		return receitaRepository.getReceitaByNome(nomeReceita.replaceAll(" ", " <-> ")).get();
	}

}
