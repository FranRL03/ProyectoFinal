package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
	

	@Id
	@GeneratedValue
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaDeVenta;
	
	private double total;
	
	@OneToMany
	private LineaVenta lineaV;
	
	@Builder.Default
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany (mappedBy = "venta",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private  List<LineaVenta> lineaVenta = new ArrayList<LineaVenta>();
	
	/*public void addToVenta(LineaVenta lineaVent) {
		this.lineaV = lineaVent;
		lineaVent.getVenta().add(lineaVent);;
	}

	public void removeFromVenta(LineaVenta lineaVent) {
		lineaVent.getVenta().remove(this);
		this.lineaV = null; 
	} */

}
