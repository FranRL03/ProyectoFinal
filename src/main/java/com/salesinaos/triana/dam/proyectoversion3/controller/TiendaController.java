package com.salesinaos.triana.dam.proyectoversion3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.excepciones.ProductoCompradoException;
import com.salesinaos.triana.dam.proyectoversion3.formbeans.SearchBean;
import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.VentaServicio;

@Controller
@RequestMapping("/ventas")
public class TiendaController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private VentaServicio vs;
	
	public TiendaController(ProductoServicio productoServicio, VentaServicio ventaServicio) {
		this.productoServicio = productoServicio;
		this.vs = ventaServicio;
	}
	
	@GetMapping("/adminPro")
	public String listarTodosProductos (Model model) {
		model.addAttribute("products", productoServicio.findAll());
		
		model.addAttribute("searchForm", new SearchBean());
		
		return "AdminProductos";
	}
	
	@PostMapping("/search")
	  public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
	  	model.addAttribute("products", productoServicio.findByNombre(searchBean.getSearch()));
	  return "AdminProductos";
	  }
	
	@GetMapping("/deleteV2/{id}")
	public String borrarProductoCompleto(@PathVariable("id") long id){
		productoServicio.delete(id);
		return "redirect:/ventas/adminPro";
	}
	
	@GetMapping("/delete/{id}")
	public String borrarProducto(@PathVariable("id") long id) {
		
		int numCompras = vs.comprarVentaProducto(id);
	    if (numCompras > 0) {
	        throw new ProductoCompradoException("Este producto no se puede borrar");
	    }else {
	    	productoServicio.restarCantidadProducto(id, 1);
	    }
	    
		
		return "redirect:/ventas/adminPro";
	}
	
	@GetMapping("/nuevoProducto")
	public String mostrarFormularioProducto(Model model) {
		model.addAttribute("producto", new Producto());
		
		return "FormularioNuevoProducto";
	}
	
	@PostMapping("/addProducto")
	public String procesarFormularioProducto(@ModelAttribute("producto") Producto p) {
		productoServicio.add(p);
		return "redirect:/ventas/adminPro";
	}
	
	@GetMapping("/editarProducto/{id}")
	public String mostrarFormularioEdicionProducto(@PathVariable("id") long id, Model model) {
		
		Producto editPro = productoServicio.findById(id);
		
		if (editPro != null) {
			model.addAttribute("producto", editPro);
			return "FormularioNuevoProducto";
		} else {
			return "redirect:/ventas/adminPro";
		}	
		
	}
	
	@PostMapping("/editarProducto")
	public String procesarFormularioEdicion(@ModelAttribute("producto") Producto p) {
		productoServicio.edit(p);
		return "redirect:/ventas/adminPro";
	}
	
	@GetMapping("/datos")
	public String listarDatosVentas(Model model) {
		model.addAttribute("ventas", vs.findAll());
		return "Ventas";
	}
	
	@GetMapping("/deleteVenta/{idVenta}")
	public String borrarVenta(@PathVariable("idVenta") long idVenta) {
		vs.deleteById(idVenta);
		return "redirect:/ventas/datos";
	}

}
