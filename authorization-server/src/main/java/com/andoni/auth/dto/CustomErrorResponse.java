package com.andoni.auth.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
