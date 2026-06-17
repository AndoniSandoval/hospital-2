package com.andoni.pacientes.servicies;

import com.andoni.commons.dto.PacienteRequest;
import com.andoni.commons.dto.PacienteResponse;
import com.andoni.commons.services.CrudService;

public interface PacienteService extends CrudService<PacienteRequest, PacienteResponse>{
	
	PacienteResponse obtenerPacientePorIdSinEstado(Long id);
}
