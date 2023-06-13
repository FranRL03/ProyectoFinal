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

	@GetMapping("/nuevaPapeleta")
	public String mostrarFormulario(Model model) {
		model.addAttribute("papeleta", new Papeleta());

		model.addAttribute("hermanos", hs.findAll());
		
		return "FormularioPapeleta";

	}

	@GetMapping("/papeleta/hermanos")
	public String listarTodosHermanos(Model model) {
		model.addAttribute("hermanos", hs.findAll());
		return "SacarPapeleta";
	}

	/* @PostMapping("/searchV4")
	public String searchHermano(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {
		model.addAttribute("hermanos", hs.findByNombre(searchBean.getSearch()));

		return "/papeleta/buscarHermano";
	} 

	@PostMapping("/buscarHermano")
	public String verificarHermanoEnLista(@ModelAttribute("papeleta") Papeleta p, String nombre) {
		Hermano her = new Hermano();

		

		p.setFechaPapeleta(LocalDate.now());

		if (hs.findByNombre(nombre) != null) {
			
			List<Hermano> herBuscado = hs.findByNombre(nombre);

			Papeleta papeleta = papeletaServicio.add(p);

			papeletaServicio.save(papeleta);
			her.addPapeleta(papeleta);
			herBuscado.add(her);

			return "redirect:/admin/gestion";
		} else {
			throw new NombreNoEncontradoException("Nombre no encontrado");
		}
	} */
	
	@PostMapping("/addPapeleta")
	public String procesarFormulario(@ModelAttribute("papeleta") Papeleta p) {
		papeletaServicio.add(p);
		return "redirect/papeleta/hermanos";
	}


	@ModelAttribute("total_papeletas")
	public Double papeletasTotal() {

		return papeletaServicio.sumarPapeletas();

	}

}
