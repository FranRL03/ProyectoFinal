package com.salesinaos.triana.dam.proyectoversion3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.model.Papeleta;
import com.salesinaos.triana.dam.proyectoversion3.service.HermanoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.PapeletaServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.SacarPapeleta;

@Controller
@RequestMapping("/papeleta")
public class PapeletaController {

	@Autowired
	private PapeletaServicio papeletaServicio;

	@Autowired
	private SacarPapeleta sp;

	@Autowired
	private HermanoServicio hs;

	public PapeletaController(PapeletaServicio service, SacarPapeleta sp, HermanoServicio hs) {
		this.papeletaServicio = service;
		this.sp = sp;
		this.hs = hs;
	}

	@GetMapping("/nuevaPapeleta")
	public String mostrarFormulario(Model model) {
		model.addAttribute("papeleta", new Papeleta());

		return "FormularioPapeleta";
	}

	 @PostMapping("/buscarHermano")
	    public String buscarHermano(@RequestParam("nombre") String nombre, RedirectAttributes redirectAttributes) {
	        Hermano hermano = (Hermano) hs.findByNombre(nombre);
	        
	        if (hermano == null) {
	            // Manejar el caso en el que no se encuentre el hermano
	            // Puedes agregar un mensaje de error a redirectAttributes si deseas mostrarlo en la página
	            redirectAttributes.addFlashAttribute("error", "No se encontró el hermano");
	            return "redirect:/papeleta/sacarPapeleta";
	        } else {
	            redirectAttributes.addFlashAttribute("hermano", hermano);
	            return "redirect:/papeleta/procesarPapeleta";
	        }
	    }
	    
	    @PostMapping("/procesarPapeleta")
	    public String procesarPapeleta(@ModelAttribute("hermano") Hermano hermano,
	                                   @ModelAttribute("papeleta") Papeleta papeleta) {
	        sp.SacarPapeleta();
	        
	        return "redirect:/";
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
	    

}
