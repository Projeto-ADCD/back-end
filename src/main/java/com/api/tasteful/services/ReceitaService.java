package com.api.tasteful.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.tasteful.DAOs.ReceitaRepository;
import com.api.tasteful.entities.Receita;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository<Receita, Integer> receitaRepository;

	/**
	 * Retorna uma lista do tamanho 'size' de receitas
	 * de acordo com a página 'page'
	 * 
	 * @return Lista com todas as receitas por página
	 */
	public List<Receita> getReceitas(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = receitaRepository.findAll(paging);
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();
	}
	
	private String fixTag(String tag) {
		String fixed = tag.replaceAll(" ", " <-> ");	
		
		return fixed;
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Receita> sortReceitasByTempo(int page, int size, boolean ascending, String tag) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = null;
		
		if (tag == "") {
			if (ascending)
				pagedResult = receitaRepository.sortReceitasByTempoASC(paging);
			else 
				pagedResult = receitaRepository.sortReceitasByTempoDESC(paging); 
		} else {
			String fixTag = fixTag(tag);
			if (ascending)
				pagedResult = receitaRepository.sortReceitasByTempoTagASC(fixTag, paging);
			else 
				pagedResult = receitaRepository.sortReceitasByTempoTagDESC(fixTag, paging); 
		}
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Receita> sortReceitasByPorcao(int page, int size, boolean ascending, String tag) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = null;
		
		if (tag == "") {
			if (ascending)
				pagedResult = receitaRepository.sortReceitasByPorcaoASC(paging);
			else 
				pagedResult = receitaRepository.sortReceitasByPorcaoDESC(paging); 
		} else {
			String fixTag = fixTag(tag);
			
			if (ascending)
				pagedResult = receitaRepository.sortReceitasByPorcaoTagASC(fixTag, paging);
			else 
				pagedResult = receitaRepository.sortReceitasByPorcaoTagDESC(fixTag, paging); 
		}
		
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();
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
	 * Deve retornar uma lista de receitas que contém os ingredientes da primeira
	 * lista e absolutamente não pode conter nenhum ingrediente da segunda lista.
	 * 
	 * @param contem_ingredientes     lista de ingredientes que o usuário deseja ter
	 *                                na receita
	 * @param nao_contem_ingredientes lista de ingredientes que não podem estar
	 *                                presentes na receita
	 * @return Uma lista de receitas de acordo com os ingredientes que devem ter
	 *         (lista 1) e os ingredientes que não devem ter (lista 2)
	 */
	public List<Receita> filtrarReceitasGenerico(String[] contem_ingredientes, 
			String[] nao_contem_ingredientes, 
			int page, int size, 
			String paramOrdem, boolean ascending) {
		
		String ingredientes = filtrarReceitasPorIngredientes(contem_ingredientes);
		String nao_ingredientes = filtrarReceitasSemIngredientes(nao_contem_ingredientes);

		String filtro = ingredientes;

		if (filtro == "") {
			filtro = nao_ingredientes;
		} else if (nao_ingredientes != "") {
			filtro += " & " + nao_ingredientes;
		}

		
		if (paramOrdem == "") 
			return filtrarReceitas(filtro, page, size);
		else 
			return filtrarReceitasOrdenado(filtro, page, size, paramOrdem, ascending);
				
	}

	public List<Receita> filtrarReceitasOrdenado(String filtro, int page, int size, String paramOrdem, boolean ascending) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = null;
		
		if (paramOrdem.equals("tempo")) {
			if (ascending)
				pagedResult = receitaRepository.filtrarReceitasSortedTempoASC(filtro, paging);
			else
				pagedResult = receitaRepository.filtrarReceitasSortedTempoDESC(filtro, paging);
		} else {
			if (ascending)
				pagedResult = receitaRepository.filtrarReceitasSortedPorcaoASC(filtro, paging);
			else
				pagedResult = receitaRepository.filtrarReceitasSortedPorcaoDESC(filtro, paging);
		}
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();
	}
	
	public List<Receita> filtrarReceitas(String filtro, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = receitaRepository.filtrarReceitas(filtro, paging);
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();
	}
	
	/**
	 * Função auxiliar para transfomar a lista de ingredientes no formato de query
	 * que o SQL espera receber
	 * 
	 * @param contem_ingredientes lista de string com os ingredientes que precisam
	 *                            ter na receita
	 * @return Uma string no formato de query do SQL com todos os ingredientes da
	 *         busca
	 */
	private String filtrarReceitasPorIngredientes(String[] contem_ingredientes) {
		String ingredientes = "";

		if (contem_ingredientes != null && contem_ingredientes.length > 0) {

			int tamanho = contem_ingredientes.length;
			for (int i = 0; i < tamanho - 1; i++) {
				ingredientes += contem_ingredientes[i].replaceAll(" ", " <-> ") + " & ";
			}

			ingredientes += contem_ingredientes[tamanho - 1].replaceAll(" ", " <-> ");
		}

		return ingredientes;
	}

	/**
	 * Função auxiliar para transfomar a lista de **nao_ingredientes** no formato de
	 * query que o SQL espera receber
	 * 
	 * @param nao_contem_ingredientes lista de string com os ingredientes que não
	 *                                podem estar na receita
	 * @return Uma string no formato de query do SQL com todos os ingredientes da
	 *         busca
	 */
	private String filtrarReceitasSemIngredientes(String[] nao_contem_ingredientes) {
		String ingredientes = "";

		if (nao_contem_ingredientes != null && nao_contem_ingredientes.length > 0) {
			int tamanho = nao_contem_ingredientes.length;
			String aux = "";
			
			for (int i = 0; i < tamanho - 1; i++) {
				aux = "!(" + nao_contem_ingredientes[i].replaceAll(" ", " <-> ");
				ingredientes += aux + "s) & " + aux + ") & ";
			}
			
			aux = "!(" + nao_contem_ingredientes[tamanho - 1].replaceAll(" ", " <-> ");
			ingredientes += aux + "s) & " + aux + ")";
		}

		return ingredientes;
	}

	/**
	 * Busca receitas no banco de dados que contenham a palavra chave recebida no
	 * título da receita
	 * 
	 * @param nomeReceita Palavra-chave para fazer a busca por "nome de receita"
	 * @return Uma lista com todas as receitas que correspondam à palavra chave
	 *         inserida
	 */
	public List<Receita> getReceitaByNomeGenerico(String nomeReceita, int page, int size, String paramOrdem, boolean ascending) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = null;
		
		nomeReceita = nomeReceita.replaceAll(" ", " <-> ");
		
		if (paramOrdem == "") {
			pagedResult = receitaRepository.getReceitaByNome(nomeReceita, paging); 
		} else {
			pagedResult = getReceitaByNomeSorted(nomeReceita, page, size, paramOrdem, ascending);
		}	
		
		if (pagedResult.hasContent())
			return pagedResult.getContent();
		return new ArrayList<Receita>();

	}
	public Page<Receita> getReceitaByNomeSorted(String nomeReceita, int page, int size, String paramOrdem, boolean ascending) {
		Pageable paging = PageRequest.of(page, size);
		Page<Receita> pagedResult = null;
				
		if (paramOrdem.equals("tempo")) {
			if (ascending)
				pagedResult = receitaRepository.getReceitaByNomeTempoASC(nomeReceita, paging); 
			else 
				pagedResult = receitaRepository.getReceitaByNomeTempoDESC(nomeReceita, paging);
		} else {

			if (ascending)
				pagedResult = receitaRepository.getReceitaByNomePorcaoASC(nomeReceita, paging); 
			else 
				pagedResult = receitaRepository.getReceitaByNomePorcaoDESC(nomeReceita, paging);
		}
		
		return pagedResult;
	}
}
