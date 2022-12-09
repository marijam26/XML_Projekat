package project.xml.z1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


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

    public static TTipZiga fromValue(String v) {
        for (TTipZiga c: TTipZiga.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
