package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.LineaDeVenta;
import com.salesinaos.triana.dam.proyectoversion3.model.Venta;
import com.salesinaos.triana.dam.proyectoversion3.repo.LineaDeVentaRepositorio;

@Service
public class LineaDeVentaServicio extends BaseService<LineaDeVenta, Long, LineaDeVentaRepositorio> {
	
	@Autowired
	private LineaDeVentaRepositorio lineaVentaRepo;
	
	@Autowired
	private VentaServicio ventaServicio;
	
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
		//Seteamos la unidad del producto restandole una
		lineaV.setUnidades(lineaV.getUnidades()-1);
		
		//cogemos la venta de la linea de venta
		Venta v = lineaV.getVenta();	
		//al borrar una linea de venta tendremos que setear el precio total de la venta
		//restandole el precio del producto
		v.setPrecioTotal(lineaV.getVenta().getPrecioTotal() - lineaV.getProducto().getPvp());
		
		//se edita la venta para que se quede guardado todo este proceso
		ventaServicio.edit(v);
		return edit(lineaV);
		
		
		
		
	} */

}
