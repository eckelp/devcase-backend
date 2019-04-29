package com.vsm.devcase.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@DiscriminatorValue(value = "CLIENTE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "Todos os detalhes sobre o cliente. ")
public class Cliente extends Pessoa implements Serializable{

	
	private static final long serialVersionUID = 6563998789568431756L;
	
	public Cliente() {
		super();
		
		this.pontuacaoTotal = BigDecimal.ZERO;
		
	}
	
	
	@Column(name = "pontuacao_total")
	private BigDecimal pontuacaoTotal;

	public BigDecimal getPontuacaoTotal() {
		return this.pontuacaoTotal != null ? this.pontuacaoTotal : BigDecimal.ZERO;
	}

	public void setPontuacaoTotal(BigDecimal pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}

	public void somarPontuacao(Pontuacao pontuacao) {
		
		this.setPontuacaoTotal(this.getPontuacaoTotal().add(pontuacao.getPontos()));
		
	}

	@Override
	public String toString() {
		return "Cliente [pontuacaoTotal=" + pontuacaoTotal + "]";
	}
	
	
	
	
	
	
	
	
}
