package com.salesinaos.triana.dam.proyectoversion3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;
import com.salesinaos.triana.dam.proyectoversion3.repo.HermanoRepositorio;


@Service
public class HermanoServicio extends BaseService<Hermano, Long, HermanoRepositorio>{

	@Autowired
	private HermanoRepositorio hermanoRepo;

	public HermanoServicio(HermanoRepositorio hp) {
		this.hermanoRepo = hp;
	}

	// AÑADIR UN HERMANO

	public Hermano add(Hermano h) {
		return hermanoRepo.save(h);
	}

	// BORRAR UN HERMANO

	public void delete(Hermano h) {
		hermanoRepo.delete(h);
	}

	// BORRAR POR ID

	public void deleteById(long numHer) {
		hermanoRepo.deleteById(numHer);
	}

	// EDITAR UN HERMANO

	public Hermano edit(Hermano h) {

		return hermanoRepo.save(h);
	}

	// DEVOLVER TODOS LOS HERMANOS

	public List<Hermano> findAll() {
		return hermanoRepo.findAll();
	}

	// BUSCAR UN HERMANO POR SU ID

	public Hermano findById(long numHer) {
		return hermanoRepo.findById(numHer).orElse(null);
	}

	public double sumarCuotas() {

		return hermanoRepo.sumaDeCuotas();

	}

	public List<Hermano> findByNombre(String nombre) {
		return hermanoRepo.findByNombreContainingIgnoreCase(nombre);		
	}
	
	public List<Hermano> findByNombreOrApellidos(String nombre, String apellidos) {
		
		return hermanoRepo.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre, apellidos);
	}
	
	public long findByNumHer(long numHer) {
		return hermanoRepo.numHermano();
	}

}
