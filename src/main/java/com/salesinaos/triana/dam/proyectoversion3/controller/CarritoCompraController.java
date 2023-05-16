package com.salesinaos.triana.dam.proyectoversion3.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.service.CarritoCompraAServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;

@Controller
public class CarritoCompraController {

	@Autowired
	private CarritoCompraAServicio carritoServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	public void ShoppingCarritoController(CarritoCompraAServicio carritoServicio, ProductoServicio productoServicio) {
		this.carritoServicio = carritoServicio;
		this.productoServicio = productoServicio;
	}

	@GetMapping("/carrito")
	public String showCarrito(Model model) {

		if (model.addAttribute("products", carritoServicio.getProductsInCart()) == null)
			return "redirect:/";
		return "CestaCarrito";
	}

	@GetMapping("/productoCarrito/{id}")
	public String productoCarrito(@PathVariable("id") Long id, Model model) {

		carritoServicio.addProducto(productoServicio.findById(id));

		return "redirect:/carrito";
	}

	@GetMapping("/borrarProducto/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		carritoServicio.removeProducto(productoServicio.findById(id));
		return "redirect:/carrito";
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {

		Map<Producto, Integer> carrito = carritoServicio.getProductsInCart();
		double total = 0.0;
		if (carrito != null) {
			for (Producto p : carrito.keySet()) {

				total += p.getPvp() * carrito.get(p);
			}
			return total;
		}

		return 0.0;
	}

	@ModelAttribute("mediaVenta")
	public Double calcularMediaVenta() {
		return carritoServicio.calcularPrecioMedioDeUnaVenta(totalCarrito());
	}
	
	@PostMapping("/comprado")
    public String checkout(){
    	  	carritoServicio.comprobarCompraRealizada();
    return "redirect:";
    }

}
