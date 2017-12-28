package com.maldiny.spring.boot.webservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maldiny.spring.boot.webservices.ws.GetPersonajeRequest;
import com.maldiny.spring.boot.webservices.ws.GetPersonajeResponse;


@Endpoint
public class PersonajeEndpoint {
	private static final String NAMESPACE_URI = "http://ws.webservices.boot.spring.maldiny.com";

	private PersonajeRepository PersonajeRepository;

	@Autowired
	public PersonajeEndpoint(PersonajeRepository PersonajeRepository) {
		this.PersonajeRepository = PersonajeRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonajeRequest")
	@ResponsePayload
	public GetPersonajeResponse getPersonaje(@RequestPayload GetPersonajeRequest request) {
		GetPersonajeResponse response = new GetPersonajeResponse();
		response.setPersonaje(PersonajeRepository.findPersonaje(request.getNombre()));

		return response;
	}
}