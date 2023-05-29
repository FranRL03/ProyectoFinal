package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="hermano", fetch = FetchType.EAGER)
	@Builder.Default
	private List<Papeleta> papeleta = new ArrayList<>();
	
	public void addPapeleta(Papeleta papeleta) {
	    papeleta.setHermano(this);
	    this.papeleta.add(papeleta);
	}
	
	public void removeFromPapeleta(Papeleta papeleta) {
	    papeleta.setHermano(null);
	    this.papeleta.remove(papeleta);
	}

	
}
	
	
