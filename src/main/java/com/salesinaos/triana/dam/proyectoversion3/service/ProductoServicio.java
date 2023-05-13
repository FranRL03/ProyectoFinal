package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.Producto;
import com.salesinaos.triana.dam.proyectoversion3.repo.ProductoRepositorio;

@Service
public class ProductoServicio extends BaseService <Producto, Long, ProductoRepositorio> {
	
	@Autowired
	private ProductoRepositorio productoRepo;
	
	public ProductoServicio(ProductoRepositorio pr) {
		this.productoRepo = pr;
	}
	
	public Producto add(Producto p) {
		return productoRepo.save(p);
	}
	
	public void delete(Producto p) {
		productoRepo.delete(p);
	}
	
	public void delete(long id) {
		productoRepo.deleteById(id);
	}
	
	public Producto edit(Producto p) {
		return productoRepo.save(p);
	}
	
	public List<Producto> findAll() {
		return productoRepo.findAll();
	}
	
	public Producto findById(long id) {
		return productoRepo.findById(id).orElse(null);
	}
	
	/**
	 * Al borrar un producto si hay más de uno 
	 * le restmos una cantidad a la cantidad total.
	 * 
	 * @param id
	 * @param cantidad
	 * @return
	 */
	public Producto restarCantidadProducto(long id , int cantidad) {
		Producto p = findById(id);
		p.setCantidad(p.getCantidad() - cantidad);
		return edit(p);
	}
	
	/**
	 * Busca un producto por el nombre
	 * 
	 * @param nombre
	 * @return devuelve el producto buscado
	 */
	public List<Producto> findByNombre(String nombre) {
		return productoRepo.findByNombreContainingIgnoreCase(nombre);
	}


}
