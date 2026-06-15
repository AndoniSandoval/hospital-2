package com.andoni.serviciob;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {
	
	@GetMapping
    public String mensaje() {
        return "Hola desde servicio B";
    }
}
