package com.vsm.devcase.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.repository.PontuacaoRepository;

@Service
public class PontuacaoService extends ApiService<Pontuacao, Integer>{

	@Autowired
	private PontuacaoRepository repository;
	
	public Pontuacao findByValorBetween(BigDecimal valor) {
		
		Pontuacao pontuacao = this.repository.findBetweenValorVenda(valor);
		
		return pontuacao != null ? pontuacao : new Pontuacao();
	}
	
}
