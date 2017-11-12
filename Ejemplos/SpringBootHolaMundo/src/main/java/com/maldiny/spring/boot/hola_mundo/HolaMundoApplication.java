package com.maldiny.spring.boot.hola_mundo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HolaMundoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolaMundoApplication.class, args);
	}
	
	@Bean 
	public String saluda(){
		System.out.println("Hola mundo...");
		return "";
	}
	
}
