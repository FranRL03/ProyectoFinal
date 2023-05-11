package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "Principal";
	}

}
