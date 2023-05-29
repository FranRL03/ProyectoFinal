package com.salesinaos.triana.dam.proyectoversion3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesinaos.triana.dam.proyectoversion3.model.Venta;

public interface VentaRepositorio extends JpaRepository<Venta, Long>{
	
	@Query("select count(v) from Venta v join v.lineaDeVenta lv where lv.producto.id = :id")
	public int contarVentas(@Param("id") Long id);

	
}
