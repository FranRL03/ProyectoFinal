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
import com.salesinaos.triana.dam.proyectoversion3.model.Venta;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarritoCompraAServicio {

	//private ProductoRepositorio productoRepo;
	
	@Autowired
	private ProductoServicio producServicio;

	@Autowired
	private VentaServicio ventaServicio;

	@Autowired
	private LineaDeVentaServicio lineaVentaServicio;


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
    
    public void comprobarCompraRealizada() {
    	Venta v = new Venta();
    	LineaDeVenta lineaV;
    	v.setFechaVenta(LocalDate.now());
    	double precioT =0.0;
    	
    	//la condición del if es comprobar si la lista NO está vacía
    	//por eso hemos puesto "!"
    	if(!productos.isEmpty()) {
    		ventaServicio.save(v);
    		
    		//Recorremos con un for este Map donde cada objeto (Producto) esta asociado 
    		//por un valor (cantidad) 
    		for(Map.Entry <Producto, Integer> lineaDeVenta : productos.entrySet()) {
    			
    			//al sacar del for el producto y la cantidad creamos la linea de pedido
    			lineaV = LineaDeVenta.builder()
    					.producto(lineaDeVenta.getKey())
    					.unidades(lineaDeVenta.getValue())
    					.precioUnidades(lineaDeVenta.getKey().getPvp()*lineaDeVenta.getValue())
    					.build();
    			
    			//una vez creada la linea de venta la añadimos a la venta con el método helper
    			lineaV.addVenta(v);
    			
    			//restamos el Stock en la linea de mercancía, es decir, restamos la cantidad que hay en la tienda
    			producServicio.restarCantidadProducto(lineaDeVenta.getKey().getId(), lineaDeVenta.getValue());
    			
    			lineaVentaServicio.save(lineaV);
    			
    			precioT += (lineaDeVenta.getKey().getPvp() * lineaDeVenta.getValue());
    			
    		}
    		
    		v.setPrecioTotal(precioT);
    		ventaServicio.save(v);
    	}
    	
    	productos.clear();
    }
    
    public double calcularPrecioMedioDeUnaVenta(double total) {
		return total / productos.size();
	}

}
