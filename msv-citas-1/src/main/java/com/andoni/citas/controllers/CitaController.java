package com.andoni.citas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.andoni.citas.dto.CitaRequest;
import com.andoni.citas.dto.CitaResponse;
import com.andoni.citas.services.CitaService;
import com.andoni.commons.controllers.CommonController;

@RestController
public class CitaController extends CommonController<CitaRequest,CitaResponse,CitaService>{

	public CitaController(CitaService service) {
		super(service);
	}
}
