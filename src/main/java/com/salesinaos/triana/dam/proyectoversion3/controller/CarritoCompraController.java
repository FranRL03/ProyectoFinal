package com.salesinaos.triana.dam.proyectoversion3.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesinaos.triana.dam.proyectoversion3.excepciones.CarritoVacioException;
import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.model.Usuario;
import com.salesinaos.triana.dam.proyectoversion3.repo.UsuarioRepositorio;
import com.salesinaos.triana.dam.proyectoversion3.service.CarritoCompraAServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.ProductoServicio;
import com.salesinaos.triana.dam.proyectoversion3.service.VentaServicio;

@Controller
@RequestMapping("/shop")
public class CarritoCompraController {

	@Autowired
	private CarritoCompraAServicio carritoServicio;

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private UsuarioRepositorio userRepo;

	@Autowired
	private VentaServicio vs;

	@Autowired
	public void ShoppingCarritoController(CarritoCompraAServicio carritoServicio, ProductoServicio productoServicio,
			VentaServicio ventaServicio) {
		this.carritoServicio = carritoServicio;
		this.productoServicio = productoServicio;
		this.vs = ventaServicio;
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

		return "redirect:/shop/carrito";
	}

	@GetMapping("/borrarProducto/{id}")
	public String removeProductFromCart(@PathVariable("id") Long id) {

		carritoServicio.removeProducto(productoServicio.findById(id));
		return "redirect:/shop/carrito";
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

	@ModelAttribute("descuento")
	public Double calcularDescuento() {
		return carritoServicio.calcularDescuento(totalCarrito());
	}

	@ModelAttribute("subtotal")
	public double calcularSubTotal() {

		return carritoServicio.calcularSubTotal();
	}

	@PostMapping("/comprado")
	public String checkout(@AuthenticationPrincipal Usuario user) {

		if (carritoServicio.getProductsInCart().isEmpty()) {
			throw new CarritoVacioException("Carrito vacío");

		} else {
			userRepo.save(user);
			carritoServicio.comprobarCompraRealizada(user);

			return "redirect:/user/tienda";
		}
	}

	@GetMapping("/historial")
	public String listarDatosVentas(Model model) {
		model.addAttribute("ventas", vs.findAll());
		return "HistorialVentas";
	}
}
