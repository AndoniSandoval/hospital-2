package com.andoni.citas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.andoni.citas.dto.CitaRequest;
import com.andoni.citas.dto.CitaResponse;
import com.andoni.citas.services.CitaService;
import com.andoni.commons.controllers.CommonController;

import jakarta.validation.constraints.Positive;

@RestController
public class CitaController extends CommonController<CitaRequest, CitaResponse, CitaService> {

	public CitaController(CitaService service) {
		super(service);
	}
	
	@PatchMapping("/{idCita}/estado/{idEstado}")
    public ResponseEntity<Void> actualizarEstadoCita(
            @PathVariable @Positive(message = "El idCita debe ser positivo") Long idCita,
            @PathVariable @Positive(message = "El idEstado debe ser positivo") Long idEstado) {

        service.actualizarEstadoCita(idCita, idEstado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id-medico/{idMedico}/citas-asignadas")
    public ResponseEntity<Void> medicoTieneCitasAsignadas(
            @PathVariable @Positive(message = "El idMedico debe ser positivo") Long idMedico) {

        service.medicoTieneCitasAsignados(idMedico);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/paciente/{idPaciente}/tiene-citas-activas")
    public ResponseEntity<Boolean> tieneCitasActivas(
    		@PathVariable Long idPaciente) {
        return ResponseEntity.ok(service.pacienteTieneCitasActivas(idPaciente));
    }
}
