package com.vsm.devcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Cidade;
import com.vsm.devcase.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	
	public List<Cidade> findAll(){
		return this.repository.findAllByOrderByNomeAsc();
	}
	
}
