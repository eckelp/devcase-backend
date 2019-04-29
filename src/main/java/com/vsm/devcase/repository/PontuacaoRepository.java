package com.vsm.devcase.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vsm.devcase.model.Pontuacao;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Integer>{

	@Query(
		"SELECT p FROM Pontuacao p WHERE ?1 between p.valorMinimo and p.valorMaximo"
	)
	public Pontuacao findBetweenValorVenda(BigDecimal valor);
	
	
}
