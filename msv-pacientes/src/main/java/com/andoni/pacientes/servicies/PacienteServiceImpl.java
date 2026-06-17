package com.andoni.pacientes.servicies;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andoni.commons.clients.CitasClient;
import com.andoni.commons.dto.PacienteRequest;
import com.andoni.commons.dto.PacienteResponse;
import com.andoni.commons.enums.EstadoRegistro;
import com.andoni.commons.exceptions.RecursoNoEncontradoException;
import com.andoni.pacientes.entities.Paciente;
import com.andoni.pacientes.mappers.PacienteMapper;
import com.andoni.pacientes.repositories.PacienteRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class PacienteServiceImpl implements PacienteService{
	
	private final PacienteRepository pacienteRepository;
	private final PacienteMapper pacienteMapper;
	private final CitasClient citaClient;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<PacienteResponse> listar() {
		log.info("Listado de todos los pacientes activos solicitado");
		return pacienteRepository.findByEstadoRegistro(EstadoRegistro.ACTIVO).stream()
				.map(pacienteMapper::entidadAResponse).toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public PacienteResponse obtenerPacientePorIdSinEstado(Long id) {
		log.info("Buscando paciente sin estado con id: {}", id);

		return pacienteMapper.entidadAResponse(pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente no encontrado con id: " + id)));
	}
	
	@Override
	@Transactional(readOnly = true)
	public PacienteResponse obtenerPorId(Long id) {
		return pacienteMapper.entidadAResponse(obtenerPacienteActivoOException(id));
	}

	@Override
	public PacienteResponse registar(PacienteRequest request) {
		log.info("Registrando un nuevo paciente: {}", request.nombre());

		validarDatosUnicos(request);

		Paciente paciente = pacienteMapper.requestAEntidad(request);

		paciente.asignarImc();
		paciente.generarNumExpediente();

		pacienteRepository.save(paciente);

		log.info("Nuevo paciente registrado: {}", paciente.getNombre());

		return pacienteMapper.entidadAResponse(paciente);
	}

	@Override
	public PacienteResponse actualizar(PacienteRequest request, Long id) {
		log.info("Actualizando paciente con id: {}", id);

		Paciente paciente = obtenerPacienteActivoOException(id);

		validarSinCitasActivas(id);

		validarCambiosUnicos(request, id);

		paciente.actualizar(
				request.nombre(),
				request.apellidoPaterno(),
				request.apellidoMaterno(),
				request.edad(),
				request.peso(),
				request.estatura(),
				request.email(),
				request.telefono(),
				request.direccion());

		log.info("Paciente actualizado con exito: {}", id);

		return pacienteMapper.entidadAResponse(paciente);
	}

	@Override
	public void eliminar(Long id) {
		log.info("Eliminando paciente con id: {}", id);

		Paciente paciente = obtenerPacienteActivoOException(id);

		validarSinCitasActivas(id);

		paciente.eliminar();

		log.info("Paciente eliminado con id: {}", id);
	}
	
	private Paciente obtenerPacienteActivoOException(Long id) {
		log.info("Buscando paciente con estado {} con id: {}", EstadoRegistro.ACTIVO, id);

		return pacienteRepository.findByIdAndEstadoRegistro(id, EstadoRegistro.ACTIVO)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente activo no encontrado con id: " + id));
	}

	private void validarSinCitasActivas(Long idPaciente) {
		log.info("Validando que el paciente con id: {} no tenga citas activas", idPaciente);

		if (citaClient.tieneCitasActivas(idPaciente))
			throw new IllegalStateException(
					"No se puede modificar el paciente con id: " + idPaciente + " porque tiene citas activas");
	}

	private void validarDatosUnicos(PacienteRequest request) {
		log.info("Validando email unico ...");

		if (pacienteRepository.existsByEmailIgnoreCaseAndEstadoRegistro(request.email().trim(), EstadoRegistro.ACTIVO))
			throw new IllegalArgumentException("Ya existe un paciente activo con el email: " + request.email());

		log.info("Validando telefono unico ...");

		if (pacienteRepository.existsByTelefonoAndEstadoRegistro(request.telefono().trim(), EstadoRegistro.ACTIVO))
			throw new IllegalArgumentException("Ya existe un paciente activo registrado con el telefono: " + request.telefono());
	}

	private void validarCambiosUnicos(PacienteRequest request, Long id) {
		log.info("Validando email unico ...");

		if (pacienteRepository.existsByEmailIgnoreCaseAndEstadoRegistroAndIdNot(request.email().trim(), EstadoRegistro.ACTIVO, id))
			throw new IllegalArgumentException("Ya existe un paciente activo con el email: " + request.email());

		log.info("Validando telefono unico ...");

		if (pacienteRepository.existsByTelefonoAndEstadoRegistroAndIdNot(request.telefono().trim(), EstadoRegistro.ACTIVO, id))
			throw new IllegalArgumentException("Ya existe un paciente activo registrado con el telefono: " + request.telefono());
	}

}
