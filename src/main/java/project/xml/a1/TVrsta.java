package project.xml.a1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


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
