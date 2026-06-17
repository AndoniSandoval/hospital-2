package com.andoni.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.andoni.pacientes", "com.andoni.commons"})
@EnableFeignClients
public class MsvPacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvPacientesApplication.class, args);
	}

}
