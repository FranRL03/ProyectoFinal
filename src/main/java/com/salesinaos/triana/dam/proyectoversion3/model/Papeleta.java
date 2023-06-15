package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	private String titular, tipo;
	private double precio;
	private long numHer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPapeleta;
	
	@ManyToOne
	private Hermano hermano;
}
