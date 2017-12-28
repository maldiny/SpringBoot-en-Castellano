//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.12.26 a las 10:27:51 PM CET 
//


package com.maldiny.spring.boot.webservices.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personaje" type="{http://ws.webservices.boot.spring.maldiny.com}personaje"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personaje"
})
@XmlRootElement(name = "getPersonajeResponse")
public class GetPersonajeResponse {

    @XmlElement(required = true)
    protected Personaje personaje;

    /**
     * Obtiene el valor de la propiedad personaje.
     * 
     * @return
     *     possible object is
     *     {@link Personaje }
     *     
     */
    public Personaje getPersonaje() {
        return personaje;
    }

    /**
     * Define el valor de la propiedad personaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Personaje }
     *     
     */
    public void setPersonaje(Personaje value) {
        this.personaje = value;
    }

}
