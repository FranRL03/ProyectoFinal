package com.salesinaos.triana.dam.proyectoversion3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;

public interface HermanoRepositorio extends JpaRepository <Hermano, Long> {
	
	public  List<Hermano> findByNombreContainingIgnoreCase(String nombre);

}
