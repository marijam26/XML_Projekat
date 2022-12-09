package project.xml.a1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "TFormat_zapisa")
@XmlEnum
public enum TFormatZapisa {

    @XmlEnumValue("opticki_disk")
    OPTICKI_DISK("opticki_disk"),
    @XmlEnumValue("stampani_tekst")
    STAMPANI_TEKST("stampani_tekst"),
    @XmlEnumValue("ostalo")
    OSTALO("ostalo");
    private final String value;

    TFormatZapisa(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TFormatZapisa fromValue(String v) {
        for (TFormatZapisa c: TFormatZapisa.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
