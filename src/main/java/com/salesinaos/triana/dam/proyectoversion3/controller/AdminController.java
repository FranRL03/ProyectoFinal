package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.repo.HermanoRepositorio;
import com.salesinaos.triana.dam.proyectoversion3.service.HermanoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class AdminController {
	
	@Autowired
	private HermanoServicio hermanoServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	public AdminController(HermanoServicio service, ProductoServicio productoServicio) {
		this.hermanoServicio = service;
		this.productoServicio = productoServicio;
	}
	
	@GetMapping("/initAdmin")
	public String index() {
		return "Principal";
	} 
	
	@GetMapping("/admin")
	public String listarTodos (Model model) {
		model.addAttribute("hermanos", hermanoServicio.findAll());
				return "ViewAdmin";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("hermano", new Hermano());
		
		String[] provincia = new String[] { "Huelva", "Sevilla", "Cádiz", "Córdoba",
				"Granada", "Málaga", "Jaén", "Almería"}; 
		model.addAttribute("provincia", provincia);
		
		String[] vivienda = new String[] { "Casa", "Piso", "Parcela"}; 
		model.addAttribute("vivienda", vivienda);
		
		return "FormularioNuevoHermano";
	}
	
	@PostMapping("/addHermano")
	public String procesarFormulario(@ModelAttribute("hermano") Hermano h) {
		hermanoServicio.add(h);
		return "redirect:/admin";
	}
	
	@GetMapping("/editar/{numHer}")
	public String mostrarFormularioEdicion(@PathVariable("numHer") long numHer, Model model) {
		
		Hermano aEditar = hermanoServicio.findById(numHer);
		
		String[] provincia = new String[] { "Huelva", "Sevilla", "Cádiz", "Córdoba",
				"Granada", "Málaga", "Jaén", "Almería"}; 
		model.addAttribute("provincia", provincia);
		
		String[] vivienda = new String[] { "Casa", "Piso", "Parcela"}; 
		model.addAttribute("vivienda", vivienda);
		
		if (aEditar != null) {
			model.addAttribute("hermano", aEditar);
			return "FormularioNuevoHermano";
		} else {
			return "redirect:/admin";
		}	
		
	}
	
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano h) {
		hermanoServicio.edit(h);
		return "redirect:/admin";
	}
	
	@GetMapping("/borrar/{numHer}")
	public String borrar(@PathVariable("numHer") long numHer) {
		hermanoServicio.deleteById(numHer);
		return "redirect:/admin";
	}
	
	@ModelAttribute("cuota_total")
	public Double cuotaTotal() {
		
		hermanoServicio.sumarCuotas();
		
		return 0.0;
	}
	
	
	//=====================================================================================
	
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
