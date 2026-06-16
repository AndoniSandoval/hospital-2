package com.andoni.citas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andoni.citas.dto.CitaRequest;
import com.andoni.citas.dto.CitaResponse;
import com.andoni.citas.entities.Cita;
import com.andoni.citas.enums.EstadoCita;
import com.andoni.citas.mappers.CitaMapper;
import com.andoni.citas.repositories.CitaRepository;
import com.andoni.commons.clients.MedicoClient;
import com.andoni.commons.dto.MedicoResponse;
import com.andoni.commons.enums.DisponibilidadMedico;
import com.andoni.commons.enums.EstadoRegistro;
import com.andoni.commons.exceptions.EntidadRelacionadaException;
import com.andoni.commons.exceptions.RecursoNoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CitaServiceImpl implements CitaService {
	
	private final CitaRepository citaRepository;
	private final CitaMapper citaMapper;
	private final MedicoClient medicoClient;
	private final List<EstadoCita> ESTADOS_INVALIDOS_REGISTROS_ASIGNADOS =
			List.of(EstadoCita.PENDIENTE, EstadoCita.CONFIRMADA, EstadoCita.EN_CURSO);
	
	@Override
	@Transactional(readOnly = true)
	public List<CitaResponse> listar() {
		log.info("Listado de todas las citas");
		return citaRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
				.map(cita -> citaMapper.entidadAResponse(
						cita,
						null,
						obtenerMedicoSinEstado(cita.getIdMedico())
						)
					).toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public CitaResponse obtenerPorId(Long id) {
		Cita cita = obtenerCitaActivaOException(id);
		
		return citaMapper.entidadAResponse(cita,null,obtenerMedicoSinEstado(cita.getIdMedico()));
	}
	
	@Override
	public CitaResponse registar(CitaRequest request) {
		log.info("Registrando nueva cita: {} ", request);
		
		MedicoResponse medico = obtenerMedicoActivo(request.idMedico());
		
		validarDisponibilidadMedico(medico.idDisponibilidad());
		
		//PacienteResponse
		
		Cita cita = citaMapper.requestAEntidad(request);
		
		citaRepository.save(cita);
		
		log.info("Cita registrada correctamente");
		
		return citaMapper.entidadAResponse(cita, null, obtenerMedicoSinEstado(cita.getIdMedico()));
	}

	@Override
	public CitaResponse actualizar(CitaRequest request, Long id) {
		Cita cita = obtenerCitaActivaOException(id);
		
		log.info("Actualizando cita con id: {}", id);
		
		MedicoResponse medico = obtenerMedicoActivo(request.idMedico());
		
		validarDisponibilidadMedico(medico.idDisponibilidad());
		
		if(!cita.getIdMedico().equals(request.idMedico()))
			validarPacienteTieneRegistrosAsignados(request.idPaciente());
		
		cita.actualizar(
				request.idPaciente(), 
				request.idMedico(), 
				request.fechaCita(), 
				request.sintomas());
		
		log.info("Cita actualizada con id: {}", id);
			
		return citaMapper.entidadAResponse(
				cita, null, medico);
	}
	
	@Override
	public void eliminar(Long id) {
		Cita cita = obtenerCitaActivaOException(id);
		
		log.info("Eliminando cita con id: {}", id);
		
		cita.eliminar();
		log.info("Cita con id {} ha sido marcada como eliminada", id);
		
	}
	
	private Cita obtenerCitaActivaOException(Long id) {
		log.info("Buscando cita activa con id: {}", id);
		
		return citaRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO).orElseThrow(
				() -> new RecursoNoEncontradoException("Cita activa no encontrada con el id: " + id));
				
	}
	
	private MedicoResponse obtenerMedicoActivo(Long idMedico) {
		log.info("Buscando medico activo con id: {} en el servicio remoto...");
		return medicoClient.obtenerMedicoActivoPorId(idMedico);
	}
	
	private MedicoResponse obtenerMedicoSinEstado(Long idMedico) {
		log.info("Buscando medico activo con id: {} en el servicio remoto...");
		return medicoClient.obtenerMedicoSinEstadoPorId(idMedico);
	}
	
	private void validarDisponibilidadMedico(Long idDisponibilidad) {
		log.info("Validando si el medico se encuentra en estado: {}", DisponibilidadMedico.DISPONIBLE);
		
		if(DisponibilidadMedico.DISPONIBLE.getCodigo().equals(idDisponibilidad))
			throw new IllegalStateException("El medico no se encuentra en estado: " + DisponibilidadMedico.DISPONIBLE);
	}
	
	private void validarPacienteTieneRegistrosAsignados(Long idPaciente) {
		log.info("Validand si el paciente con id {} tiene una cita activa con los estados: {}",
				idPaciente, ESTADOS_INVALIDOS_REGISTROS_ASIGNADOS);
		
		if(citaRepository.existsByIdPacienteAndEstadoRegistroAndEstadoCitaIn(
				idPaciente, EstadoRegistro.ACTIVO, ESTADOS_INVALIDOS_REGISTROS_ASIGNADOS))
			throw new EntidadRelacionadaException("No se puede registrar la cita ya que el paciente solo puede tener una" + "cita activa con los estados de: "+ESTADOS_INVALIDOS_REGISTROS_ASIGNADOS );
	}
	
	

}
