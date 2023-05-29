package com.salesinaos.triana.dam.proyectoversion3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesinaos.triana.dam.proyectoversion3.model.Papeleta;

public interface PapeletaRepositorio extends JpaRepository<Papeleta, Long> {
	
	@Query("select sum(p.precio) from Papeleta p")
	double sumaDePapeletas();

}
