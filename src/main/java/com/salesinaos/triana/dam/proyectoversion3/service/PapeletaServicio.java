package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.model.Papeleta;
import com.salesinaos.triana.dam.proyectoversion3.model.Venta;
import com.salesinaos.triana.dam.proyectoversion3.repo.PapeletaRepositorio;
import com.salesinaos.triana.dam.proyectoversion3.repo.VentaRepositorio;

@Service
public class PapeletaServicio extends BaseService<Papeleta, Long, PapeletaRepositorio> {
	
	@Autowired
	private PapeletaRepositorio papeletaRepo;
	
	public PapeletaServicio (PapeletaRepositorio pr) {
		this.papeletaRepo = pr;
	}
	
	public Papeleta add (Papeleta p) {
		return papeletaRepo.save(p);
		
	}
	
	public void delete (Papeleta p) {
		papeletaRepo.delete(p);
		
	}
	
	public void deleteById(long numPapeleta) {
		papeletaRepo.deleteById(numPapeleta);
	}
	
	public Papeleta edit (Papeleta p) {
		return papeletaRepo.save(p);
		
	}
	
	public List<Papeleta> findAll(){
		return papeletaRepo.findAll();
	}
	
	public Papeleta findById(long numPapeleta) {
		return papeletaRepo.findById(numPapeleta).orElse(null);
	}
	
	public double sumarPapeletas() {
		
		return papeletaRepo.sumaDePapeletas();
	}
	

}
