package com.vsm.devcase.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import com.vsm.devcase.model.Cliente;
import com.vsm.devcase.model.User;
import com.vsm.devcase.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200")
@Api(
		value = "Resource para clientes cadastradas", 
		description = "Resource para consumo de clientes")
public class ClienteResource {

	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	
	@GetMapping
	@ApiOperation(value = "Retorna a lista de clientes. Quando o parâmetro 'nome' é informada, retorna os clientes que contém este nome", response = List.class)
	public List<Cliente> index(@PathParam(value="name") String nome) {
		
		return nome != null ? this.service.findByNomeContaining(nome) : this.service.findAll();
		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o cliente por id", response = Cliente.class)
	public ResponseEntity<Cliente> show(@PathVariable("id") Integer id){
		
		Cliente cliente = this.service.findById(id);
		
		return ResponseEntity.ok().body(cliente);
		
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastrar o cliente", response = Cliente.class)
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente Cliente, HttpServletResponse response){
		
		Cliente savedCliente = this.service.create(Cliente);
		
		eventPublisher.publishEvent(new CreatedResourceEvent(this,  response, savedCliente.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remover o cliente com id")
	public void delete(@PathVariable("id") Integer id) {
		
		this.service.delete(id);
		
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar o cliente", response = User.class)
	public ResponseEntity<Cliente> update(@PathVariable("id") Integer id, @Valid @RequestBody Cliente Cliente){
		
		Cliente savedCliente = this.service.update(id, Cliente);
		
		return ResponseEntity.ok().body(savedCliente);
		
	}
		
}
