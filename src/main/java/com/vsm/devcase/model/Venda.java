package com.vsm.devcase.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vsm.devcase.interfaces.Countable;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "venda")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel(description = "Todos os detalhes sobre a venda. ")
public class Venda implements Countable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "data")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date  data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "func_vendedor_id")
	private Funcionario vendedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "func_caixa_id")
	private Funcionario operadorCaixa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "forma_pgto_id")
	private FormaPagamento formaPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pontuacao_id")
	private Pontuacao pontuacao;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "venda_id")
    private List<ProdutoVenda> produtos = new ArrayList<>();
	
	@Transient
	private Long total;
	
	@JsonIgnore
	@Transient 
	private BigDecimal valorTotal;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Funcionario getVendedor() {
		return vendedor;
	}


	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}


	public Funcionario getOperadorCaixa() {
		return operadorCaixa;
	}


	public void setOperadorCaixa(Funcionario operadorCaixa) {
		this.operadorCaixa = operadorCaixa;
	}


	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public Long getTotal() {
		return total;
	}


	public Pontuacao getPontuacao() {
		return pontuacao;
	}


	public void setPontuacao(Pontuacao pontuacao) {
		this.pontuacao = pontuacao;
	}


	public List<ProdutoVenda> getProdutos() {
		return produtos;
	}

	
	

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	


	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}


	public void setProdutos(List<ProdutoVenda> produtos) {
		this.produtos = produtos;
	}


	@Override
	public String toString() {
		return "Venda [id=" + id + ", data=" + data + ", cliente=" + cliente + ", vendedor=" + vendedor
				+ ", operadorCaixa=" + operadorCaixa + ", formaPagamento=" + formaPagamento + ", total=" + total
				+ ", pontuacao=" + pontuacao + ", produtos=" + produtos + "]";
	}

	
	
	

}
