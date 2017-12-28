package com.maldiny.spring.boot.springbatch.model;

public class Personaje {

	private String nombre;
	private int edad;
	private String genero;
	
	public Personaje() {
		super();
	}

	public Personaje(String nombre, int edad, String genero) {
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Personaje [nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + "]";
	}
	
}
