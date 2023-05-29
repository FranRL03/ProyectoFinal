package com.salesinaos.triana.dam.proyectoversion3.excepciones;

public class NombreNoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NombreNoEncontradoException() {
		
	}
	
	public NombreNoEncontradoException(String mensaje) {
		
		super("Nombre no encontrado");
	}

}
