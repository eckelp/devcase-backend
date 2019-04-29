package com.vsm.devcase.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vsm.devcase.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{

	
	@Query(
		"SELECT count(v.id) as total, v.cliente.sexo as sexo FROM Venda v GROUP BY v.cliente.sexo"
	)
	public List<Venda> countGroupBySexo();
	
	@Query(
			"SELECT count(v.id) as total, v.cliente.sexo as sexo FROM Venda v where v.data between :dti and :dtf GROUP BY v.cliente.sexo"
		)
		public List<Venda> countGroupBySexo(Date dti, Date dtf);
	
	@Query(
		"SELECT sum(pv.quantidade * p.preco) as valorTotal " + 
	     "FROM Venda v " +
   "INNER JOIN ProdutoVenda pv on pv.venda = v " +
   "INNER JOIN Produto p on p = pv.produto " +
	    "WHERE v.id = :id"
	)
	public BigDecimal findValorTotalVenda(Integer id);

	
	@Query("select v from Venda v where v.data between :dti and :dtf")
	public List<Venda> findAllDataBetween(Date  dti, Date dtf);
	
	
}
