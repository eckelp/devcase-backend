package com.vsm.devcase.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.Cidade;
import com.vsm.devcase.model.User;
import com.vsm.devcase.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cidades")
@CrossOrigin(origins = "http://localhost:4200")
@Api(
		value = "Resource para cidades cadastradas", 
		description = "Resource para consumo de cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de cidades", response = User.class)
	public ResponseEntity<List<Cidade>> index() {
		
		List<Cidade> list = this.service.findAll();
		
		return new ResponseEntity<List<Cidade>>(list, HttpStatus.OK);
		
	}
	
}
