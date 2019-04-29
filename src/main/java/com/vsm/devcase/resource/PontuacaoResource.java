package com.vsm.devcase.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.event.CreatedResourceEvent;
import com.vsm.devcase.model.Pontuacao;
import com.vsm.devcase.service.PontuacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pontuacoes")
@CrossOrigin(origins = "http://localhost:4200")
@Api(
		value = "Resource para pontuações cadastradas", 
		description = "Resource para consumo de pontuações")
public class PontuacaoResource {
	
	
	@Autowired
	private PontuacaoService pontuacaoService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de pontuações", response = List.class)
	public List<Pontuacao> index() {
		
		return this.pontuacaoService.findAll();
		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna a pontuação por id", response = Pontuacao.class)
	public ResponseEntity<Pontuacao> show(@PathVariable("id") Integer id){
		
		Pontuacao pontuacao = pontuacaoService.findById(id);
		
		return ResponseEntity.ok().body(pontuacao);
		
	}

	
	@PostMapping
	@ApiOperation(value = "Cadastrar a pontuação", response = Pontuacao.class)
	public ResponseEntity<Pontuacao> create(@Valid @RequestBody Pontuacao pontuacao, HttpServletResponse response) {
		
		Pontuacao savedPontuacao = pontuacaoService.create(pontuacao);
		
		eventPublisher.publishEvent(new CreatedResourceEvent(this,  response, savedPontuacao.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPontuacao);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remover a pontuação")
	public void delete(@PathVariable("id") Integer id) {
		
		this.pontuacaoService.delete(id);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar a pontuação", response = Pontuacao.class)
	public ResponseEntity<Pontuacao> update(@PathVariable("id") Integer id, @Valid @RequestBody Pontuacao pontuacao){
		
		Pontuacao savedPontuacao = this.pontuacaoService.update(id, pontuacao);
		
		return ResponseEntity.ok().body(savedPontuacao);
		
	}
	
	

}
