package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesinaos.triana.dam.proyectoversion3.excepciones.CarritoVacioException;
import com.salesinaos.triana.dam.proyectoversion3.excepciones.ProductoCompradoException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(CarritoVacioException.class)
	public String carritoException(Model model, CarritoVacioException cve) {
		
		model.addAttribute("excep", cve);
		
		return "CarritoException";
		
	}
	
	@ExceptionHandler(ProductoCompradoException.class)
	public String productoException(Model model, ProductoCompradoException pce) {
		
		model.addAttribute("prodcutoExcep", pce);
		
		return "ProductoException";
	}

}
