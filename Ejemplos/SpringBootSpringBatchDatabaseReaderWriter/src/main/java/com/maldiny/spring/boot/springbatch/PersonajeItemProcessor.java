package com.maldiny.spring.boot.springbatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.maldiny.spring.boot.springbatch.domain.Personajes;

public class PersonajeItemProcessor implements ItemProcessor<Personajes, Personajes> {

    private static final Logger log = LoggerFactory.getLogger(PersonajeItemProcessor.class);

    @Override
    public Personajes process(final Personajes person) throws Exception {
    	
    	person.setNombre(person.getNombre().toUpperCase());

    	System.err.println(person);
    	
        return person;
    }

}