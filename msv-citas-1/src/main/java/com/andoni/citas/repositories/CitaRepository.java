package com.andoni.citas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andoni.citas.entities.Cita;
import com.andoni.citas.enums.EstadoCita;
import com.andoni.commons.enums.EstadoRegistro;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{
	
	List<Cita> findByEstadoRegistro(EstadoRegistro estadoRegistro);
	
	Optional<Cita> findByIdAndEstadoRegistro(Long id, EstadoRegistro estadoRegistro);
	
	boolean existsByIdPacienteAndEstadoRegistroAndEstadoCitaIn(
			Long idPaciente, EstadoRegistro estadoRegistro, List<EstadoCita> estadosCita);
	
	boolean existsByIdMedicoAndIgnoreCaseAndEstadoCitaIn(
			Long idMedico, EstadoRegistro estadoRegistro, List<EstadoCita> estadosCita);
}
