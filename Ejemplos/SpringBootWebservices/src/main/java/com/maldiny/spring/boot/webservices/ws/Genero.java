//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.12.26 a las 10:27:51 PM CET 
//


package com.maldiny.spring.boot.webservices.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para genero.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="genero">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="masculino"/>
 *     &lt;enumeration value="femenino"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "genero")
@XmlEnum
public enum Genero {

    @XmlEnumValue("masculino")
    MASCULINO("masculino"),
    @XmlEnumValue("femenino")
    FEMENINO("femenino");
    private final String value;

    Genero(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Genero fromValue(String v) {
        for (Genero c: Genero.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
