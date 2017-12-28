package com.maldiny.spring.boot.springbatch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Personajes")
public class Personajes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
	private String nombre;
	private String edad;
	private String genero;
	
	public Personajes() {
		super();
	}

	public Personajes(String nombre, String edad, String genero) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + "]";
	}
	
}
