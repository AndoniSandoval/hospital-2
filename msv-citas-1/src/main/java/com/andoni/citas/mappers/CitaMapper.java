package com.andoni.citas.mappers;

import org.springframework.stereotype.Component;

import com.andoni.citas.dto.CitaRequest;
import com.andoni.citas.dto.CitaResponse;
import com.andoni.citas.entities.Cita;
import com.andoni.commons.dto.DatosMedico;
import com.andoni.commons.dto.MedicoResponse;
import com.andoni.commons.mappers.CommonMapper;

@Component
public class CitaMapper implements CommonMapper<CitaRequest, CitaResponse, Cita>{

	@Override
	public Cita requestAEntidad(CitaRequest request) {
		if (request == null) return null;
		
		return Cita.crear(
				request.idPaciente(),
				request.idMedico(),
				request.fechaCita(),
				request.sintomas()
				);
		
	}
	
	@Override
	public CitaResponse entidadAResponse(Cita entidad) {
		if (entidad == null) return null;
		
		return new CitaResponse(
				entidad.getId(),
				null,
				null,
				entidad.getFechaCita(),
				entidad.getSintomas(),
				entidad.getEstadoCita().getDescripcion()
		);
		
	}
	
	public CitaResponse entidadAResponse(Cita entidad, Object paciente, MedicoResponse medico) {
		if (entidad == null) return null;
		
		return new CitaResponse(
				entidad.getId(),
				null,
				medicoResponseADatosMedico(medico),
				entidad.getFechaCita(),
				entidad.getSintomas(),
				entidad.getEstadoCita().getDescripcion()
		);
	}
	
	private DatosMedico medicoResponseADatosMedico(MedicoResponse medico) {
		if (medico == null) return null;
		
		return new DatosMedico(
				medico.nombre(),
				medico.cedulaProfesional(),
				medico.especialidad()
				);
	}
}