package project.xml.p1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "TVrsta_punomocnika")
@XmlEnum
public enum TVrstaPunomocnika {

    @XmlEnumValue("za_zastupanje")
    ZA_ZASTUPANJE("za_zastupanje"),
    @XmlEnumValue("za_prijem_pismena")
    ZA_PRIJEM_PISMENA("za_prijem_pismena");
    private final String value;

    TVrstaPunomocnika(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TVrstaPunomocnika fromValue(String v) {
        for (TVrstaPunomocnika c: TVrstaPunomocnika.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
