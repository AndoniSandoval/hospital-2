package com.andoni.citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
@EnableEurekaClient
public class MsvCitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvCitasApplication.class, args);
	}

}
