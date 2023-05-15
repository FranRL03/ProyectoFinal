package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesinaos.triana.dam.proyectoversion3.service.CarritoCompraAServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class CarritoCompraController {
	
	@Autowired
	private CarritoCompraAServicio carritoServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	public void ShoppingCarritoController (CarritoCompraAServicio carritoServicio, ProductoServicio productoServicio) {
		this.carritoServicio = carritoServicio;
		this.productoServicio = productoServicio;
	}
	
	

}
