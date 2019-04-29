package com.vsm.devcase.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vsm.devcase.exceptions.ProdutoSemEstoqueException;
import com.vsm.devcase.exceptions.ResourceNotFoundException;
import com.vsm.devcase.model.Produto;
import com.vsm.devcase.model.ProdutoVenda;

@Service
@Transactional
public class ProdutoService extends ApiService<Produto, Integer>{

	
	public List<ProdutoVenda> validList(List<ProdutoVenda> produtosVenda) throws ResourceNotFoundException {
		
		List<ProdutoVenda> valid = new ArrayList<ProdutoVenda>(); 
		
		produtosVenda.forEach(
			(produtoVenda) ->  {
				
				ProdutoVenda validItem = 
					ProdutoVenda
						.build()
							.setProduto( this.findById(produtoVenda.getProduto().getId()) )
							.setQuantidade(produtoVenda.getQuantidade());
				
				valid.add(validItem);
			}
		);
		
		return valid;
		
	}
	
	public boolean validarQuantidade(Produto p, BigDecimal quantidade) throws ProdutoSemEstoqueException {
		
		
		if( p.getQuantidade().subtract(quantidade).compareTo(BigDecimal.ZERO) < 0 ) {
			throw new ProdutoSemEstoqueException(
				"O produto " + p.getDescricao() + " possui apenas " + p.getQuantidade() + " em estoque. " +
				"Quantidade solicitada: " + quantidade
			);
		};
		
		return true;
		
	}
	
	public Produto findById(Integer id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
	}

	public void validarQuantidadeProdutos(List<ProdutoVenda> produtos) throws ProdutoSemEstoqueException {
		
		for(ProdutoVenda produtoVenda : produtos) {
			this.validarQuantidade(produtoVenda.getProduto(), produtoVenda.getQuantidade());
		}
		
	}

	public void atualizarListaEstoque(List<ProdutoVenda> produtos) {
		
		for(ProdutoVenda produtoVenda : produtos) {
			
			Produto p = produtoVenda.getProduto();
			
			p.setQuantidade(p.getQuantidade().subtract(produtoVenda.getQuantidade()));
			
			this.save(p);
		}
		
	}
	
}
