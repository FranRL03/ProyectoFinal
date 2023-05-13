package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.LineaDeVenta;
import com.salesinaos.triana.dam.proyectoversion3.repo.LineaDeVentaRepositorio;

@Service
public class LineaDeVentaServicio extends BaseServiceImpl <LineaDeVenta, Long, LineaDeVentaRepositorio> {
	
	@Autowired
	private LineaDeVentaRepositorio lineaVentaRepo;
	
	/**
	 * Muestra todas las lineas de venta
	 */
	
	public List<LineaDeVenta> findAll() {
		return lineaVentaRepo.findAll();
	}

	/**
	 * Editas una linea de venta
	 */
	
	public LineaDeVenta edit(LineaDeVenta lineaVenta) {
		return lineaVentaRepo.save(lineaVenta);
	}
	
	
	
	/* public LineaDeVenta borrarCantidadLineaVenta(long id) {
		// llamamos al método para que busque la linea de venta por el id
		LineaDeVenta lineaV = findById(id);
		
		
		
		
	} */

}
