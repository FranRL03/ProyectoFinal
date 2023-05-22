/*package com.salesinaos.triana.dam.proyectoversion3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.formbeans.SearchBean;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class ProductoController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProductoServicio productService;
	
	@GetMapping({"/tienda"})
	public String listarProductos (Model model) {
		
		model.addAttribute("products", productService.findAll());
		
		model.addAttribute("searchForm", new SearchBean());
		return "Tienda";

	}
	
	@PostMapping("/search")
	  public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
	  	model.addAttribute("products", productService.findByNombre(searchBean.getSearch()));
	  
	  return "Tienda";
	  }
	
	
} */
