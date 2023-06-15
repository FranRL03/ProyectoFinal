package com.salesinaos.triana.dam.proyectoversion3.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.model.Papeleta;
import com.salesinaos.triana.dam.proyectoversion3.service.HermanoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.PapeletaServicio;

@Controller
@RequestMapping("/papeleta")
public class PapeletaController {

	@Autowired
	private PapeletaServicio papeletaServicio;

	@Autowired
	private HermanoServicio hs;

	public PapeletaController(PapeletaServicio service, HermanoServicio hs) {
		this.papeletaServicio = service;
		this.hs = hs;
	}

	@GetMapping("/lista")
	public String listarTodos(Model model) {
		model.addAttribute("papeletas", papeletaServicio.findAll());

		return "AdminPapeletas";
	}
	
	@GetMapping("/papeleta/hermanos")
	public String listarTodosHermanos(Model model) {
		model.addAttribute("hermanos", hs.findAll());

		return "SacarPapeleta";
	}

	@GetMapping("/nuevaPapeleta/{numHer}")
	public String mostrarFormulario(@PathVariable("numHer") long numHer, Model model) {
		
		Hermano aEditar = hs.findById(numHer);

		if (aEditar != null) {
			model.addAttribute("papeleta", new Papeleta());
			model.addAttribute("hermanos", hs.findAll());
			model.addAttribute("hermano", aEditar);
			
			return "FormularioPapeleta";
		}else {
			return "redirect:/papeleta/papeleta/hermanos";
		}

	}


	@PostMapping("/addPapeleta")
	public String procesarFormulario(@ModelAttribute("papeleta") Papeleta p) {
		p.setFechaPapeleta(LocalDate.now());
		Hermano her = hs.findById(p.getNumHer());
		p.setHermano(her);
		papeletaServicio.add(p);
		return "redirect:/admin/gestion";
	}

	@ModelAttribute("total_papeletas")
	public Double papeletasTotal() {

		return papeletaServicio.sumarPapeletas();

	}

}
