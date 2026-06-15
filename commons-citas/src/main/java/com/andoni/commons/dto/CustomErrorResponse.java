package com.andoni.commons.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
