package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>>{
	
	@Autowired
	protected R repository;
	
	public List<T> findAll() {
		return repository.findAll();
	}

	
	public T findById(ID id) {
		return repository.findById(id).orElse(null);
	}

	
	public T save(T t) {
		return repository.save(t);
	}

	
	public T edit(T t) {
		return repository.save(t);
	}

	
	public void delete(T t) {
		repository.delete(t);
		
	}

	public void deleteById(ID id) {
		repository.deleteById(id);		
	}
	

}
