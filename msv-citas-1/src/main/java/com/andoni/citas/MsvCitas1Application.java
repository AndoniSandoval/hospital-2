package com.andoni.citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.andoni.citas", "com.andoni.commons"})
public class MsvCitas1Application {

	public static void main(String[] args) {
		SpringApplication.run(MsvCitas1Application.class, args);
	}

}
