package com.andoni.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.andoni.commons.dto.MedicoResponse;

@FeignClient(name = "msv-medicos")
public interface MedicoClient {
	
	@GetMapping("/{id}")
	MedicoResponse obtenerMedicoActivoPorId(@PathVariable Long id);
	
	@GetMapping("/id-medico/{id}")
	MedicoResponse obtenerMedicoSinEstadoPorId(@PathVariable Long id);
	
	@PutMapping("/idMedico}/disponibilidad/{idDisponibilidad}")
	void actualizarDisponibilidadMedico(
			@PathVariable Long idMedico,
			@PathVariable Long idDisponibilidad);

}
