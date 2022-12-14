//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 03:42:25 PM CET 
//


package project.a1.model.a1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TVrsta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TVrsta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="knjizevno_delo"/>
 *     &lt;enumeration value="muzicko_delo"/>
 *     &lt;enumeration value="likovno_delo"/>
 *     &lt;enumeration value="racunarski_program"/>
 *     &lt;enumeration value="ostalo"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TVrsta")
@XmlEnum
public enum TVrsta {

    @XmlEnumValue("knjizevno_delo")
    KNJIZEVNO_DELO("knjizevno_delo"),
    @XmlEnumValue("muzicko_delo")
    MUZICKO_DELO("muzicko_delo"),
    @XmlEnumValue("likovno_delo")
    LIKOVNO_DELO("likovno_delo"),
    @XmlEnumValue("racunarski_program")
    RACUNARSKI_PROGRAM("racunarski_program"),
    @XmlEnumValue("ostalo")
    OSTALO("ostalo");
    private final String value;

    TVrsta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TVrsta fromValue(String v) {
        for (TVrsta c: TVrsta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
