package com.vsm.devcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsm.devcase.model.Funcionario;
import com.vsm.devcase.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements UserDetailsService{
	
	@Autowired
	private FuncionarioRepository repository;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario =  this.repository.findByUserUsername(username);
		
	
		
		 if (funcionario == null) {
	            throw new UsernameNotFoundException("Usuário não encontrado");
        }
		 
		 return funcionario;
		
	}
	
}
