package com.salesinaos.triana.dam.proyectoversion3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Producto {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String img, nombre, descripcion;
	private int cantidad;
	private double pvp;
	
	
	
	

}
