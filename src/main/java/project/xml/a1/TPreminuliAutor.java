package project.xml.a1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPreminuli_autor", propOrder = {
    "godinaSmrti"
})
@Getter
@Setter
public class TPreminuliAutor
    extends TAutor
{

    @XmlElement(name = "Godina_smrti", required = true)
    protected BigInteger godinaSmrti;

}
