package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>>{
	
	@Autowired
	protected R repository;
	
	
	/**
	 * Muestra todos los objetos de la lista
	 * 
	 * @return devuelve una lista
	 */
	public List<T> findAll() {
		return repository.findAll();
	}

	/**
	 * Busca el objeto por su id
	 * 
	 * @param id
	 * @return devuelvo el objeto o nulo si no lo ha encontrado 
	 */
	
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
