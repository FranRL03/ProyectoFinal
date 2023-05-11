package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.service.HermanoServicio;

@Controller
public class AdminController {
	
	@Autowired
	private HermanoServicio hermanoServicio;
	
	public AdminController(HermanoServicio service) {
		this.hermanoServicio = service;
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
	
	

}
