package com.vsm.devcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsm.devcase.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	public List<Cidade> findAllByOrderByNomeAsc();
	
}
