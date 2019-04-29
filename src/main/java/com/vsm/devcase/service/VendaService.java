package com.vsm.devcase.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.exceptions.ProdutoSemEstoqueException;
import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.model.ProdutoVenda;
import com.vsm.devcase.model.Venda;
import com.vsm.devcase.repository.VendaRepository;

@Service
public class VendaService extends ApiService<Venda, Integer> {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PontuacaoService pontuacaoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private VendaRepository repository;
	
	
	public List<Venda> findAll(Date  dti, Date  dtf){
		
		
		
		if(dti != null && dtf != null) {		
			return this.repository.findAllDataBetween(dti, dtf);
		}
		
		return this.repository.findAll();
		
	}
	
	public List<Venda> countGroupBySexo() {
		
		return this.repository.countGroupBySexo();
		
	}
	
	public List<Venda> countGroupBySexo(Date dti, Date dtf) {
		
		if(dti != null && dtf != null) {		
			return this.repository.countGroupBySexo(dti, dtf);
		}
		
		return this.countGroupBySexo();
		
	}
	
	
	public Venda realizarVenda(@Valid Venda venda) throws ProdutoSemEstoqueException {
		
		venda.setProdutos(this.validListProdutoVenda(venda.getProdutos()));
		
		List<ProdutoVenda> produtos = venda.getProdutos();
		
		this.produtoService.validarQuantidadeProdutos(produtos);
		
		Venda novaVenda = super.create(venda);
		
		this.atualizarEstoque(produtos);
		
		
		this.registrarPontuacao(novaVenda);
		
		return novaVenda;
		
		
		
	}
	
	
	
	private void atualizarEstoque(List<ProdutoVenda> produtos) {
		
		this.produtoService.atualizarListaEstoque(produtos);
		
	}
				
		
	private List<ProdutoVenda> validListProdutoVenda(List<ProdutoVenda> produtos) {
		return this.produtoService.validList(produtos);
	}
	
	private void registrarPontuacao(Venda venda) {
		
		Cliente cliente = this.somarPontuacao(venda);
		
		this.clienteService.update(cliente.getId(), cliente);
		
		venda.setCliente(cliente);
	}
	
	private Cliente somarPontuacao(Venda venda) {
		BigDecimal valorTotal = this.valorTotal(venda);
		
		Cliente cliente = this.clienteService.findById(venda.getCliente().getId());
		
		Pontuacao pontuacao = this.pontuacaoService.findByValorBetween(valorTotal);
				
		cliente.somarPontuacao(pontuacao);
		
		return cliente;
	}
	
	private BigDecimal valorTotal(Venda venda) {
		
		return this.repository.findValorTotalVenda(venda.getId());
				
	}
	
}
