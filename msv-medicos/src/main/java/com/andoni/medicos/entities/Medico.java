package com.andoni.medicos.entities;

import com.andoni.commons.enums.DisponibilidadMedico;
import com.andoni.commons.enums.EspecialidadMedico;
import com.andoni.commons.enums.EstadoRegistro;
import com.andoni.commons.utils.StringCustomUtils;
import com.andoni.commons.utils.ValoresNumericosUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "MEDICOS")
public class Medico {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "EDAD", nullable = false)
    private Short edad;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "TELEFONO", length = 10, nullable = false)
    private String telefono;

    @Column(name = "CEDULA_PROFESIONAL", length = 12, nullable = false)
    private String cedulaProfesional;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESPECIALIDAD", nullable = false)
    private EspecialidadMedico especialidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISPONIBILIDAD", nullable = false)
    private DisponibilidadMedico disponibilidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_REGISTRO", nullable = false)
    private EstadoRegistro estadoRegistro;
    
    public void actualizar(
            String nombre, String apellidoPaterno, String apellidoMaterno, Short edad, String email,
            String telefono, String cedulaProfesional, EspecialidadMedico especialidad) {
        validarNoEliminado();
        validarDatos(nombre, apellidoPaterno, apellidoMaterno, edad, email, telefono, cedulaProfesional, especialidad); 
        actualizarEspecialidad(especialidad); 
        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
        this.edad = edad;
        this.email = email.trim().toLowerCase();
        this.telefono = telefono.trim();
        this.cedulaProfesional = cedulaProfesional.trim();
    
    }
    public void actualizarEspecialidad(EspecialidadMedico nuevaEspecialidad) {
    	validarNoEliminado();
    	if (nuevaEspecialidad == null)
    		throw new IllegalArgumentException("La especialidad es requerida");
    	this.especialidad = nuevaEspecialidad;
    }
    
    public void actualizarDisponibilidad(DisponibilidadMedico nuevaDisponibilidad) {
    	validarNoEliminado();
    	
    	if (nuevaDisponibilidad == null)
    		throw new IllegalArgumentException("La disponibilidad es requerida");
    	this.disponibilidad = nuevaDisponibilidad;
    }
    
    private void validarDatos(
            String nombre, String apellidoPaterno, String apellidoMaterno, Short edad, String email,
            String telefono, String cedulaProfesional, EspecialidadMedico especialidad) {
    	StringCustomUtils.validarTamanio(nombre, 1, 50, "El nombre es requerido y debe contener entre 1 y 5 caracteres");
    	StringCustomUtils.validarTamanio(apellidoPaterno, 1, 50, "El apellido paterno es requerido y debe contener entre 1 y 5 caracteres");
    	StringCustomUtils.validarTamanio(apellidoMaterno, 1, 50, "El apellido materno es requerido y debe contener entre 1 y 5 caracteres");
    	StringCustomUtils.validarTamanio(email, 1, 100, "El email es requerido y debe contener entre 1 y 5 caracteres");
    	StringCustomUtils.validarTamanio(telefono, 10, 10, "El telefono es requerido y debe contener 10 digitos (0-9)");
    	StringCustomUtils.validarTamanio(cedulaProfesional, 12, 12, "La cedula es requerida y debe contener 12 caracteres");
    	ValoresNumericosUtils.validarRangoShort(edad, (short)18, (short)100, "La edad es requerida y debe tener entre 18 a 100 años");
    	
    	if (especialidad == null)
    		throw new IllegalArgumentException("La especialidad es requerida");
    }
    
    public void eliminar() {
    	validarNoEliminado();
    	this.estadoRegistro = EstadoRegistro.ELIMINADO;
    }
    
    private void validarNoEliminado() {
    	if (this.estadoRegistro == EstadoRegistro.ELIMINADO)
    		throw new IllegalStateException("El medico ya esta eliminado");
    }
}