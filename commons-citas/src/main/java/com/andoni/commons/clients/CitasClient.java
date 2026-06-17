package com.andoni.commons.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msv-citas-1")
public interface CitasClient {
	@GetMapping("/paciente/{idPaciente}/tiene-citas-activas")
    boolean tieneCitasActivas(@PathVariable Long idPaciente);
}
