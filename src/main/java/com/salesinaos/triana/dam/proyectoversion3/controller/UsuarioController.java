package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.formbeans.SearchBean;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
@RequestMapping("/user")
public class UsuarioController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping("/tienda")
	public String mostrarProductos(Model model) {
		model.addAttribute("products", productoServicio.findAll());
		
		model.addAttribute("searchForm", new SearchBean());
		
		return "Tienda";
	}
	
	@PostMapping("/searchV3")
	  public String searchHermano(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
	  	model.addAttribute("products", productoServicio.findByNombre(searchBean.getSearch()));
	  
	  return "Tienda";
	  } 

}
