package com.salesinaos.triana.dam.proyectoversion3.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
	
	/* @Id
	@GeneratedValue
	private long idLineaVenta; */
	
	@EmbeddedId
	@Builder.Default
	private LineaVentaPK lineaVentaPK = new LineaVentaPK();
	
	private int unidades;
	private double precioUnidades;
	
	@ManyToOne
	private Producto producto;
	
	@ManyToOne
	@MapsId("venta_id")
	private Venta venta;
	
	

}
