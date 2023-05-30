package com.salesinaos.triana.dam.proyectoversion3.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.excepciones.NombreNoEncontradoException;
import com.salesinaos.triana.dam.proyectoversion3.formbeans.SearchBean;
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

		return "FormularioPapeleta";
	}

	@PostMapping("/buscarHermano")
	public String verificarHermanoEnLista(@ModelAttribute("papeleta") Papeleta p, String nombre, String apellidos) {
		Hermano her = new Hermano();

		p.setFechaPapeleta(LocalDate.now());

		if (hs.findByNombreOrApellidos(nombre, apellidos) != null) {

			Papeleta papeleta = papeletaServicio.add(p);

			papeletaServicio.save(papeleta);
			her.addPapeleta(papeleta);

			return "redirect:/admin/gestion";
		} else {
			throw new NombreNoEncontradoException("Nombre no encontrado");
		}
	}

	@PostMapping("/editarPapeleta/{numPapeleta}")
	public String mostrarFormularioEdit(@PathVariable("numPapeleta") long numPapeleta, Model model) {
		Papeleta edit = papeletaServicio.findById(numPapeleta);

		if (edit != null) {
			model.addAttribute("papeleta", edit);
			return "FormularioPapeleta";
		} else {
			return "redirect:/admin/gestion";
		}
	}

	@ModelAttribute("total_papeletas")
	public Double papeletasTotal() {

		return papeletaServicio.sumarPapeletas();

	}

}
