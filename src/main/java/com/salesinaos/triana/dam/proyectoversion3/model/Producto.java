package com.salesinaos.triana.dam.proyectoversion3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String nombre, descripcion;
	private double pvp;
	private int cantidad;
	
	

}
