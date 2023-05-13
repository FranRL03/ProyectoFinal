package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
	
	List<T> findAll();
	
	Optional<T> findById(ID id);
	
	T save(T t);
	
	T edit(T t);
	
	void delete(T t);
	
	void deleteById(ID id);

}
