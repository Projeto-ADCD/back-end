package com.api.tasteful;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.tasteful.DAOs.ReceitaRepository;
import com.api.tasteful.entities.Receita;
import com.api.tasteful.services.ReceitaService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TastefulApplicationTests {

	@InjectMocks
	private ReceitaService receitaService;
	@Mock
	private ReceitaRepository<Receita, Integer> receitaRepository;

	private List<Receita> r = getReceitasMock();

	@Test
	void getReceitas() {
		Mockito.when(receitaRepository.findAll()).thenReturn(new ArrayList<Receita>());
		List<Receita> getReceitas = receitaService.getReceitas();
		assertEquals(new ArrayList<Receita>(), getReceitas);
	}

	@Test
	void getReceitas2() {
		Mockito.when(receitaRepository.findAll()).thenReturn(r);
		List<Receita> getReceitas = receitaService.getReceitas();
		System.out.println(getReceitas);
		assertEquals(r, getReceitas);
	}

	@Test
	void getReceitaById() {
		Mockito.when(receitaRepository.getReceitaById(1)).thenReturn(Optional.of(r.get(1)));
		Receita getReceitaById = receitaService.getReceitaById(1);
		assertEquals(r.get(1), getReceitaById);
	}
	
	@Test
	void getReceitaByNome() {
		List<Receita> r_teste = new ArrayList<Receita>();
		r_teste.add(r.get(2));
		
		Mockito.when(receitaRepository.getReceitaByNome("farofa")).thenReturn(Optional.of(r_teste));
		List<Receita> getReceitaByNome = receitaService.getReceitaByNome("farofa");
		assertEquals(r_teste, getReceitaByNome);
	}
	
	/*
	@Test
	void filtrarReceitas() {
		List<Receita> r_teste = new ArrayList<Receita>();
		r_teste.add(r.get(2));
		
		String[] contem = {"farofa"};
		String[] nao_contem = {"frango"};

		Mockito.when(receitaRepository.filtrarReceitas("farofa & !frango")).thenReturn(r_teste);
		List<Receita> filtrarReceitas = receitaService.filtrarReceitas(contem, nao_contem);
		assertEquals(r_teste, filtrarReceitas);
	}
	*/

	private List<Receita> getReceitasMock() {
		List<Receita> receitas = new ArrayList<Receita>();
		Receita r1 = new Receita();
		Receita r2 = new Receita();
		Receita r3 = new Receita();

		r1.setId(1);
		r2.setId(2);
		r3.setId(3);

		r1.setRecipe_json("receita 1 bolo chocolate amendoim leite");
		r2.setRecipe_json("receita 2 farofa frango");
		r3.setRecipe_json("receita 3 caipirinha limão cachaça farofa");

		receitas.add(r1);
		receitas.add(r2);
		receitas.add(r3);

		return receitas;
	}

}
