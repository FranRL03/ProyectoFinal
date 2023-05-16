package com.salesinaos.triana.dam.proyectoversion3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.formbeans.BuscarBeans;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class ProductoController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProductoServicio productService;
	
	@GetMapping({"/producto", "/list"})
	public String listarProductos (Model model) {
		
		model.addAttribute("productos", productService.findAll());
		
		model.addAttribute("searchForm", new BuscarBeans());
		return "GestionProductos";

	}
	
	@PostMapping("/buscar")
	  public String searchProducto(@ModelAttribute("searchForm") BuscarBeans buscarBean,
			 Model model){
	  	model.addAttribute("productos", productService.findByNombre(buscarBean.getSearch()));
	  
	  return "GestionProductos";
	  }
	
	
}
