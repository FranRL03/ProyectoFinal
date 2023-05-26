package com.salesinaos.triana.dam.proyectoversion3.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesinaos.triana.dam.proyectoversion3.model.LineaDeVenta;
import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.model.Usuario;
import com.salesinaos.triana.dam.proyectoversion3.model.Venta;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoCompraAServicio {

	@Autowired
	private ProductoServicio producServicio;

	@Autowired
	private VentaServicio ventaServicio;

	private Map<Producto, Integer> productos = new HashMap<>();

	/**
	 * Añade un producto al carrito pero si ya hay uno incrementará la cantidad del
	 * producto una unidad más.
	 * 
	 * @param p
	 */

	public void addProducto(Producto p) {

		if (productos.containsKey(p)) {

			productos.replace(p, productos.get(p) + 1);

		} else {

			productos.put(p, 1);
		}
	}

	/**
	 * Elimina un producto del carrito. Si la cantidad del producto es mayor a uno
	 * se borra la cantidad de una unidad. Si la cantidad del producto es una se
	 * borrará el producto.
	 * 
	 * @param p
	 */

	public void removeProducto(Producto p) {

		if (productos.containsKey(p)) {

			if (productos.get(p) > 1)

				productos.replace(p, productos.get(p) - 1);

			else if (productos.get(p) == 1) {

				productos.remove(p);
			}
		}
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista
	 */

	public Map<Producto, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(productos);
	}

	public void comprobarCompraRealizada(Usuario user) {
    	Venta v = new Venta();
    	v.setFechaVenta(LocalDate.now());
    	double precioT =0.0;
    	
    	if (!productos.isEmpty()) {
    	     LocalDate.now();
    	    ventaServicio.save(v);
    	    
    	    for (Producto p : productos.keySet()) {
    	        v.addLineaVenta((LineaDeVenta.builder()
    	            .producto(p)
    	            .unidades(productos.get(p))
    	            .precioUnidades(p.getPvp())
    	            .build()));
    	        producServicio.restarCantidadProducto(p.getId(), productos.get(p));
    	        
    	        precioT += productos.get(p) * p.getPvp();
    	    }
    	    
    	    v.setUser(user);
    	    v.setPrecioTotal(precioT);
    	    ventaServicio.save(v);
    	    productos.clear();
    	}
	}
	
	public double calcularDescuento(double total) {
		
		if (total >= 300) {
			return total - (total * 0.15);
		} else {
			return total;
		}
	}
	
	

}
