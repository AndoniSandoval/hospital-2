package com.andoni.escuela.utils;

import javax.lang.model.type.ExecutableType;
import java.math.BigDecimal;

public class ValoresNumericosUtils {

    public static <N extends Number> void validarNumeroRequerido (N numero) {
        if (numero == null)
            throw new IllegalArgumentException("El valor numerico es requerido");
    }

    public static void validarEnteroPositivo(Integer entero, String mensaje){
        validarNumeroRequerido(entero);

        if (entero < 0)
            throw new IllegalArgumentException(mensaje);
    }

    public static  void validarDecimalPositivo(BigDecimal numero, String mensaje){
        validarNumeroRequerido(numero);

        if (numero.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(mensaje);
    }
}
