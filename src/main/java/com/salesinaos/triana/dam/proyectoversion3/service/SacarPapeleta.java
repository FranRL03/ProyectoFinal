package com.salesinaos.triana.dam.proyectoversion3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesinaos.triana.dam.proyectoversion3.model.Hermano;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SacarPapeleta {
	
	@Autowired
	private HermanoServicio hs;
	
	@Autowired
	private PapeletaServicio ps;
	
	public void SacarPapeleta() {
		
		
		
		
	}
	
}


