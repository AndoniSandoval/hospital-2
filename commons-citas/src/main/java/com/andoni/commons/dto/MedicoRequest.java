package com.andoni.commons.dto;


import jakarta.validation.constraints.*;

public record MedicoRequest(

        @NotBlank(message = "El nombre es requerido")
        @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 1, max = 50, message = "El apellido paterno debe tener entre 1 y 50 caracteres")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 1, max = 50, message = "El apellido materno debe tener entre 1 y 50 caracteres")
        String apellidoMaterno,

        @NotNull(message = "La edad es requerida")
        @Min(value = 18, message = "La edad minima es 18 años")
        @Max(value = 100, message = "La edad maxima es 100 años")
        Short edad,

        @NotBlank(message = "El email es requerido")
        @Email(message = "El email debe ser valido")
        @Size(min = 1, max = 100, message = "El email no puede exceder 100 caracteres")
        String email,

        @NotBlank(message = "El telefono es requerido")
        @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe tener exactamente 10 digitos")
        String telefono,

        @NotBlank(message = "La cedula profesional es requerida")
        @Size(min = 2, max = 12, message = "La cedula profesional debe tener entre 2 y 12 caracteres")
        String cedulaProfesional,
        
        @NotNull(message = "El id de la especialidad es requerido")
        @Positive(message = "El id de la especialidad debe ser positivo")
        Long idEspecialidad

        
) {
}
