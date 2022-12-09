package project.xml.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "TVrsta_znaka")
@XmlEnum
public enum TVrstaZnaka {

    @XmlEnumValue("verbalni")
    VERBALNI("verbalni"),
    @XmlEnumValue("graficki")
    GRAFICKI("graficki"),
    @XmlEnumValue("kombinovani")
    KOMBINOVANI("kombinovani"),
    @XmlEnumValue("trodimenzionalni")
    TRODIMENZIONALNI("trodimenzionalni"),
    @XmlEnumValue("ostalo")
    OSTALO("ostalo");
    private final String value;

    TVrstaZnaka(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TVrstaZnaka fromValue(String v) {
        for (TVrstaZnaka c: TVrstaZnaka.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
