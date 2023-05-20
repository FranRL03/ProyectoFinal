package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class AdminProductoController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	public AdminProductoController(ProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}
	
	@GetMapping("/adminPro")
	public String listarTodosProductos (Model model) {
		model.addAttribute("products", productoServicio.findAll());
		return "AdminProductos";
	}
	
	@GetMapping("/delete/{id}")
	public String borrarProducto(@PathVariable("id") long id) {
		productoServicio.restarCantidadProducto(id, 1);
		return "redirect:/adminPro";
	}
	
	@GetMapping("/nuevoProducto")
	public String mostrarFormularioProducto(Model model) {
		model.addAttribute("producto", new Producto());
		
		return "FormularioNuevoProducto";
	}
	
	@PostMapping("/addProducto")
	public String procesarFormularioProducto(@ModelAttribute("producto") Producto p) {
		productoServicio.add(p);
		return "redirect:/adminPro";
	}
	
	@GetMapping("/editarProducto/{id}")
	public String mostrarFormularioEdicionProducto(@PathVariable("id") long id, Model model) {
		
		Producto editPro = productoServicio.findById(id);
		
		if (editPro != null) {
			model.addAttribute("producto", editPro);
			return "FormularioNuevoProducto";
		} else {
			return "redirect:/adminPro";
		}	
		
	}
	
	@PostMapping("/editarProducto")
	public String procesarFormularioEdicion(@ModelAttribute("producto") Producto p) {
		productoServicio.edit(p);
		return "redirect:/adminPro";
	}

}
