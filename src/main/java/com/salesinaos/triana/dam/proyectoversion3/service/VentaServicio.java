package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salesinaos.triana.dam.proyectoversion3.model.Venta;
import com.salesinaos.triana.dam.proyectoversion3.repo.VentaRepositorio;

public class VentaServicio extends BaseService<Venta, Long, VentaRepositorio>{
	
	@Autowired
	private VentaRepositorio ventaRepo;
	
	public VentaServicio(VentaRepositorio vp) {
		this.ventaRepo = vp;
	}
	
	public Venta add(Venta v) {
		return ventaRepo.save(v);
	}
	
	public void delete(Venta v) {
		ventaRepo.delete(v);
	}
	
	
	public void deleteById(long id) {
		ventaRepo.deleteById(id);
	}
	
	public Venta edit(Venta v) {
		return ventaRepo.save(v);
	}
	
	public List<Venta> findAll() {
		return ventaRepo.findAll();
	}

	public Venta findById(long id) {
		return ventaRepo.findById(id).orElse(null);
	}
}
