package com.salesinaos.triana.dam.proyectoversion3.model;

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
public class LineaDeVenta {
	
	@Id
	@GeneratedValue
	private long idLineaVenta;
	
	private int unidades;
	private double precioUnidades;
	
	@ManyToOne
	private Producto producto;
	
	@ManyToOne
	private Venta venta;
	
	//MÉTODOS HELPER
	
	/*public void addVenta(Venta v) {
		venta.setLineaDeVenta(null);
		this.venta
	}*/

}
