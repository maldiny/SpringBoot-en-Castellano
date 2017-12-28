package com.maldiny.spring.boot.springbatch.chunk;
public class MyObject {
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyObject(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyObject [id=" + id + ", name=" + name + "]";
	}
	
}