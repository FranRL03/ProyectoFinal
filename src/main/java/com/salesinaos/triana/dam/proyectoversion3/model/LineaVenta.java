package com.salesinaos.triana.dam.proyectoversion3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaVenta {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Producto producto;

	private int unidadProducto;
	private double precioCantidad; 
	
	@ManyToOne
	private Venta venta;

	
	
	public void addToVenta(Venta venta) {
		this.venta = venta;
		venta.getLineaVenta().add(this);
	}

	public void removeFromVenta(Venta venta) {
		venta.getLineaVenta().remove(this);
		this.venta = null;
	}

}
