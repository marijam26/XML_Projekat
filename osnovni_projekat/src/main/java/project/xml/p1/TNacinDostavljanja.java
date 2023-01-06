package project.xml.p1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "TNacin_dostavljanja")
@XmlEnum
public enum TNacinDostavljanja {

    @XmlEnumValue("papirna_forma")
    PAPIRNA_FORMA("papirna_forma"),
    @XmlEnumValue("elektronska_forma")
    ELEKTRONSKA_FORMA("elektronska_forma");
    private final String value;

    TNacinDostavljanja(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TNacinDostavljanja fromValue(String v) {
        for (TNacinDostavljanja c: TNacinDostavljanja.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
