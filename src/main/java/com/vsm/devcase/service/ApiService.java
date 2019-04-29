package com.vsm.devcase.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.devcase.exceptions.ResourceNotFoundException;

public abstract class ApiService<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> repository;

	
	public T create(@Valid T entity) {
		
		return this.save(entity);
				
	}
	
	
	public T findById(ID id) throws ResourceNotFoundException {
		
		return this.repository.findById(id).orElseThrow( () -> new ResourceNotFoundException() );
		
	}
	
	public List<T> findAll() {
		
		return this.repository.findAll();
		
	}
	
	public final T update(ID id, @Valid T entity) throws ResourceNotFoundException{
		
		return this.update(id, entity, "id");
		
	}
	
	public final T update(ID id, @Valid T entity, String ...ignorable) throws ResourceNotFoundException{
		
		
		T savedEntity = this.findById(id);
		
		BeanUtils.copyProperties(entity,  savedEntity, ignorable);
		
		return this.save(savedEntity);
		
	}
	
	public final T save(@Valid T entity) {
		return this.repository.save(entity);
	}
	
	public void delete(ID id) throws ResourceNotFoundException{
		
		this.findById(id);
		
		this.repository.deleteById(id);
	}
	
	
}
