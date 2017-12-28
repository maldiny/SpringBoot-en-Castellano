package com.maldiny.spring.boot.webservices;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.maldiny.spring.boot.webservices.ws.Genero;
import com.maldiny.spring.boot.webservices.ws.Personaje;

@Component
public class PersonajeRepository {
	private static final Map<String, Personaje> personajes = new HashMap<>();

	@PostConstruct
	public void initData() {
		Personaje hommer = new Personaje();
		hommer.setNombre("Hommer");
		hommer.setEdad(50);
		hommer.setGenero(Genero.MASCULINO);

		personajes.put(hommer.getNombre(), hommer);

		Personaje bart = new Personaje();
		bart.setNombre("Bart");
		bart.setEdad(8);
		bart.setGenero(Genero.MASCULINO);

		personajes.put(bart.getNombre(), bart);

		Personaje lisa = new Personaje();
		lisa.setNombre("Lisa");
		lisa.setEdad(7);
		lisa.setGenero(Genero.FEMENINO);

		personajes.put(lisa.getNombre(), lisa);
	}

	public Personaje findPersonaje(String Nombre) {
		return personajes.get(Nombre);
	}
}