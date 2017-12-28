package com.maldiny.spring.boot.springbatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.maldiny.spring.boot.springbatch.model.Personaje;

public class PersonajeItemProcessor implements ItemProcessor<Personaje, Personaje> {

    private static final Logger log = LoggerFactory.getLogger(PersonajeItemProcessor.class);

    @Override
    public Personaje process(final Personaje person) throws Exception {
    	
    	person.setNombre(person.getNombre().toUpperCase());

        return person;
    }

}