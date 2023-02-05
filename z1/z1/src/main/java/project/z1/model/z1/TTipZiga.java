//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 03:44:33 PM CET 
//


package project.z1.model.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTip_ziga.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TTip_ziga">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="individualni"/>
 *     &lt;enumeration value="kolektivni"/>
 *     &lt;enumeration value="zig_garancije"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TTip_ziga")
@XmlEnum
public enum TTipZiga {

    @XmlEnumValue("individualni")
    INDIVIDUALNI("individualni"),
    @XmlEnumValue("kolektivni")
    KOLEKTIVNI("kolektivni"),
    @XmlEnumValue("zig_garancije")
    ZIG_GARANCIJE("zig_garancije");
    private final String value;

    TTipZiga(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static TTipZiga fromValue(String v) {
        for (TTipZiga c: TTipZiga.values()) {
            System.out.println(c.value);
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
