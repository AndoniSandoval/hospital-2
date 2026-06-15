package com.andoni.servicioa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andoni.servicioa.client.MensajeClient;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
	
	private final MensajeClient mensajeClient;
	
	public SaludoController(MensajeClient mensajeClient) {
		this.mensajeClient = mensajeClient;
	}
	
	@GetMapping
	public String saludo() {
		return "Servicio A dice:" + mensajeClient.obtenerMnesaje();
	}

}
