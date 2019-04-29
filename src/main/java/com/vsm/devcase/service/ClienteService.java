package com.vsm.devcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.repository.ClienteRepository;

@Service
public class ClienteService extends ApiService<Cliente, Integer>{
	
	@Autowired
	private ClienteRepository repository;
	
	
	public List<Cliente> findByNomeContaining(String nome) {
		
		return nome != null ? this.repository.findByNomeContaining(nome) : this.findAll();
		
	}
	


}
