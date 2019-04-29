package com.vsm.devcase.resource;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.event.CreatedResourceEvent;
import com.vsm.devcase.exceptions.ProdutoSemEstoqueException;
import com.vsm.devcase.model.User;
import com.vsm.devcase.model.Venda;
import com.vsm.devcase.service.VendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vendas")
@Api(
		value = "Resource para vendas cadastradas", 
		description = "Resource para consumo de vendas")
public class VendaResource {
	
	
	@Autowired
	private VendaService service;
	

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@GetMapping
	@ResponseBody
	@ApiOperation(value = "Retorna a lista de vendas complete ou entre duas datas", response = List.class)
	public ResponseEntity<List<Venda>> index(
		@PathParam(value="dti")   @DateTimeFormat(pattern="yyyy-MM-dd") Date dti, 
		@PathParam(value = "dtf") @DateTimeFormat(pattern="yyyy-MM-dd") Date dtf
	){
		
		List<Venda> vendas = this.service.findAll(dti, dtf);
		
		return new ResponseEntity<List<Venda>>(vendas, HttpStatus.OK);
		
	}
	
	@GetMapping("/total-sexo")
	@ResponseBody
	@ApiOperation(value = "Retorna o total de vendas por sexo completo ou entre duas datas", response = List.class)
	public ResponseEntity<List<Venda>> countGroupBySexo(
		@PathParam(value="dti")   @DateTimeFormat(pattern="yyyy-MM-dd") Date dti, 
		@PathParam(value = "dtf") @DateTimeFormat(pattern="yyyy-MM-dd") Date dtf
	){
		
		List<Venda> list = this.service.countGroupBySexo(dti, dtf);
		
		return new ResponseEntity<List<Venda>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Cadastrar a venda", response = Venda.class)
	public ResponseEntity<Venda> create(@RequestBody @Valid Venda venda, HttpServletResponse response) throws ProdutoSemEstoqueException{
		
		Venda savedVenda = this.service.realizarVenda(venda);
		
		eventPublisher.publishEvent(new CreatedResourceEvent(this,  response, savedVenda.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedVenda);
		
		
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "Retorna a venda por id", response = User.class)
	public ResponseEntity<Venda> show(@PathVariable("id") Integer id) {
		
		Venda venda = this.service.findById(id);
		
		return new ResponseEntity<Venda>(venda, HttpStatus.OK);
		
	}
	
	

}
