package com.andoni.auth.dto;

public record ErrorResponse(
        int codigo,
        String mensaje
) { }
