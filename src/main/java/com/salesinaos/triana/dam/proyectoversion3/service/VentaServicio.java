package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.Venta;
import com.salesinaos.triana.dam.proyectoversion3.repo.VentaRepositorio;

@Service
public class VentaServicio extends BaseService<Venta, Long, VentaRepositorio> {

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
	
	//====================================

	public double generarNumAleatorio() {

		int num = 0;
		int ganador = 25;
		double descuento = 0.0;
		//List<Venta> v = findAll();
		
		Venta v = new Venta();

		Random rnd = new Random(System.nanoTime());

		num = rnd.nextInt(50 - 1) + 1;

		if (num >= ganador) {
			
			descuento = v.getPrecioTotal() * 0.5;
			
		/*	for (Venta venta : v) {
				
				descuento = venta.getPrecioTotal() * 0.5;
			} */
		}

		return descuento;

	}

}
