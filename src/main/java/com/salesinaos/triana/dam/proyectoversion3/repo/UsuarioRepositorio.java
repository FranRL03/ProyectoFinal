package com.salesinaos.triana.dam.proyectoversion3.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesinaos.triana.dam.proyectoversion3.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findFirstByUsername(String nombreUsuario);

}
