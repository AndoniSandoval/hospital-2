package com.andoni.commons.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PacienteRequest(
		
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
        
        @NotNull(message = "El peso es requerido")
        @DecimalMin(value = "0.1", message = "El peso debe ser mayor que 0kg")
        @DecimalMax(value = "200", message = "La peso maximo permitido es de 200 kg")
        Double peso,
        
        @NotNull(message = "La estatura es requerida")
		@DecimalMin(value = "1.0", message = "La estatura debe ser superior a 1 metro")
		@DecimalMax(value = "2.0", message = "La estatura debe ser inferior a 2 metros")
        Double estatura,
        
        @NotBlank(message = "El email es requerido")
        @Email(message = "El email debe ser valido")
        @Size(min = 1, max = 100, message = "El email no puede exceder 100 caracteres")
        String email,

        @NotBlank(message = "El telefono es requerido")
        @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe tener exactamente 10 digitos")
        String telefono,
        
        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 1, max = 150, message = "El apellido materno debe tener entre 1 y 150 caracteres")
        String direccion
		) {

}
