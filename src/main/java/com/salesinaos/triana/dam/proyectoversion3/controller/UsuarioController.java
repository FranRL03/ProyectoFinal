package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.VentaServicio;

@Controller
public class UsuarioController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private VentaServicio ventaServicio;
	
	@GetMapping("/tienda")
	public String mostrarProductos(Model model) {
		model.addAttribute("products", productoServicio.findAll());
		
		return "Tienda";
	}
	
	@GetMapping("/buscar")
	public String buscarProducto(Model model, @RequestParam String nombre) {
		model.addAttribute("productos", productoServicio.findByNombre(nombre));
		
		return "Tienda";
	}

}
