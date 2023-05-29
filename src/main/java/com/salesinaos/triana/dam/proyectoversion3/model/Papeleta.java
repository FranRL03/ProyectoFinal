package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Papeleta {
	
	@Id
	@GeneratedValue
	private long numPapeleta;
	
	private double precio;
	private String titular, tipo;
	private LocalDate año;
	
	@ManyToOne
	private Hermano hermano;
}
