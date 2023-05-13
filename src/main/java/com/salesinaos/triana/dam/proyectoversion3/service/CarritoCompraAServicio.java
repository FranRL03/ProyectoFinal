package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.repo.ProductoRepositorio;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoCompraAServicio {

	private ProductoRepositorio productoRepo;

	private Map<Producto, Integer> productos = new HashMap<>();
	
	
	
	/**
	 * Añade un producto al carrito pero si ya hay uno 
	 * incrementará la cantidad del producto una unidad más. 
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
	 * Elimina un producto del carrito.
	 * Si la cantidad del producto es mayor a uno se borra la 
	 * cantidad de una unidad.
	 * Si la cantidad del producto es una se borrará el producto.
	 * 
	 * @param p
	 */
	
	
	public void removeProducto (Producto p) {
        
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

}
