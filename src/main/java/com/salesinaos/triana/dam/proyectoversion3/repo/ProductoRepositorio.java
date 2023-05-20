package com.salesinaos.triana.dam.proyectoversion3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesinaos.triana.dam.proyectoversion3.model.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	
	public  List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
