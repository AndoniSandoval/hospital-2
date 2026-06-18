package com.andoni.citas.services;

import com.andoni.citas.dto.CitaRequest;
import com.andoni.citas.dto.CitaResponse;
import com.andoni.commons.services.CrudService;

public interface CitaService extends CrudService<CitaRequest, CitaResponse> {

	void actualizarEstadoCita(Long idCita, Long idEstadoCita);
	void medicoTieneCitasAsignados(Long idMedico);
	
	boolean pacienteTieneCitasActivas(Long idPaciente);
	
}
