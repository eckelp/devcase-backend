package com.vsm.devcase.model;

import java.beans.Transient;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "produto_venda")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "Todos os detalhes sobre o produto da venda. ")
public class ProdutoVenda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Column(name = "quantidade")
	private BigDecimal quantidade;
    
    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
	private Venda venda;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
	private Produto produto;
    
	private ProdutoVenda() {}
	
    private ProdutoVenda(Produto produto, BigDecimal quantidade) {
    	this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public static ProdutoVenda build() {
    	return new ProdutoVenda();
    }
    
    
	@Transient
	public BigDecimal precoTotal() {
		return this.getProduto().getPreco().multiply(this.getQuantidade());
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}
	
	


	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ProdutoVenda setProduto(Produto produto) {
		this.produto = produto;
		
		return this;
	}


	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public ProdutoVenda setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
		
		return this;
	}
	
	
	

	@Override
	public String toString() {
		return "ProdutoVenda [id=" + id + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVenda other = (ProdutoVenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
	

}
