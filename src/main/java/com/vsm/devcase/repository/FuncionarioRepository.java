package com.vsm.devcase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsm.devcase.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	public Optional<Funcionario> findByUserUsernameAndUserPassword(String username, String password);
	
	public Funcionario findByUserUsername(String username);
	
}
