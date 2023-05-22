package com.salesinaos.triana.dam.proyectoversion3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.repo.VentaRepositorio;
import com.salesinaos.triana.dam.proyectoversion3.service.HermanoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private HermanoServicio hermanoServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private VentaRepositorio vs;
	
	public AdminController(HermanoServicio service, ProductoServicio productoServicio) {
		this.hermanoServicio = service;
		this.productoServicio = productoServicio;
	}
	
	@GetMapping("/")
	public String index() {
		return "Principal";
	} 
	
	@GetMapping("/gestion")
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
		return "redirect:/admin/gestion";
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
			return "redirect:/admin/gestion";
		}	
		
	}
	
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("hermano") Hermano h) {
		hermanoServicio.edit(h);
		return "redirect:/admin/gestion";
	}
	
	@GetMapping("/borrar/{numHer}")
	public String borrar(@PathVariable("numHer") long numHer) {
		hermanoServicio.deleteById(numHer);
		return "redirect:/admin/gestion";
	}
	
	@ModelAttribute("cuota_total")
	public Double cuotaTotal() {
		
	return hermanoServicio.sumarCuotas();
		
		
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
		return "redirect:/admin/adminPro";
	}
	
	@GetMapping("/nuevoProducto")
	public String mostrarFormularioProducto(Model model) {
		model.addAttribute("producto", new Producto());
		
		return "FormularioNuevoProducto";
	}
	
	@PostMapping("/addProducto")
	public String procesarFormularioProducto(@ModelAttribute("producto") Producto p) {
		productoServicio.add(p);
		return "redirect:/admin/adminPro";
	}
	
	@GetMapping("/editarProducto/{id}")
	public String mostrarFormularioEdicionProducto(@PathVariable("id") long id, Model model) {
		
		Producto editPro = productoServicio.findById(id);
		
		if (editPro != null) {
			model.addAttribute("producto", editPro);
			return "FormularioNuevoProducto";
		} else {
			return "redirect:/admin/adminPro";
		}	
		
	}
	
	@PostMapping("/editarProducto")
	public String procesarFormularioEdicion(@ModelAttribute("producto") Producto p) {
		productoServicio.edit(p);
		return "redirect:/admin/adminPro";
	}
	
	//===============================================================================================
	
	@GetMapping("/datos")
	public String listarDatosVentas(Model model) {
		model.addAttribute("ventas", vs.findAll());
		return "Ventas";
	}
	
	@GetMapping("/deleteVenta/{idVenta}")
	public String borrarVenta(@PathVariable("idVenta") long idVenta) {
		vs.deleteById(idVenta);
		return "redirect:/admin/datos";
	}

}
