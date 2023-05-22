package com.salesinaos.triana.dam.proyectoversion3.excepciones;

public class CarritoVacioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarritoVacioException() {
		
	}
	
	public CarritoVacioException(String mensaje) {
		
		super ("El carrito está vacío");

	}

}
