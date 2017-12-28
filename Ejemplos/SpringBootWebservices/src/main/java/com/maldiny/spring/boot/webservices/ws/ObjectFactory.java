//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.12.26 a las 10:27:51 PM CET 
//


package com.maldiny.spring.boot.webservices.ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.maldiny.spring.boot.webservices.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.maldiny.spring.boot.webservices.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPersonajeRequest }
     * 
     */
    public GetPersonajeRequest createGetPersonajeRequest() {
        return new GetPersonajeRequest();
    }

    /**
     * Create an instance of {@link GetPersonajeResponse }
     * 
     */
    public GetPersonajeResponse createGetPersonajeResponse() {
        return new GetPersonajeResponse();
    }

    /**
     * Create an instance of {@link Personaje }
     * 
     */
    public Personaje createPersonaje() {
        return new Personaje();
    }

}
