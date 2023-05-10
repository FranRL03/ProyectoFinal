package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hermano {
	
	@Id
	@GeneratedValue
	private long numHer;
	
	private String nombre, apellidos, email, dni;
	private double cuota;
	private String cargo, domicilio, provincia, vivienda, movil;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNac, fechaAlta;

	
}
	
	
