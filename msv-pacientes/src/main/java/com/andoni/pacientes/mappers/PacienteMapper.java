package com.andoni.pacientes.mappers;

import org.springframework.stereotype.Component;

import com.andoni.commons.dto.PacienteRequest;
import com.andoni.commons.dto.PacienteResponse;
import com.andoni.commons.mappers.CommonMapper;
import com.andoni.pacientes.entities.Paciente;

@Component
public class PacienteMapper implements CommonMapper<PacienteRequest, PacienteResponse, Paciente>{

	@Override
	public Paciente requestAEntidad(PacienteRequest request) {
		if (request == null) return null;
		
		return Paciente.builder()
				.nombre(request.nombre().trim())
				.apellidoPaterno(request.apellidoPaterno().trim())
                .apellidoMaterno(request.apellidoMaterno().trim())
                .edad(request.edad())
                .peso(request.peso())
                .estatura(request.estatura())
                .email(request.email())
                .telefono(request.telefono())
                .direccion(request.direccion())
				.build();
	}

	@Override
	public PacienteResponse entidadAResponse(Paciente entidad) {
		if (entidad == null) return null;
		
		return new PacienteResponse(
				entidad.getId(),
				String.join(" ", entidad.getNombre(),
                        entidad.getApellidoPaterno(),
                        entidad.getApellidoMaterno()),
				entidad.getEdad(),
				entidad.getPeso(),
				entidad.getEstatura(),
				entidad.getImc(),
				entidad.getEmail(),
				entidad.getTelefono(),
				entidad.getDireccion(),
				entidad.getNumExpediente()
				);
	}

}
