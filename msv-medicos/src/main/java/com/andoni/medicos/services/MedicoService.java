package com.andoni.medicos.services;

import com.andoni.commons.dto.MedicoRequest;
import com.andoni.commons.dto.MedicoResponse;
import com.andoni.commons.services.CrudService;

public interface MedicoService extends CrudService<MedicoRequest, MedicoResponse >{
	
	MedicoResponse obtenerMedicoPorIdSinEstado(Long id);
	
	void actualizarDisponibilidadMedico(Long idMedico, Long idDisponibilidad);
	
}
