package project.z1.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZahtevZaZigDTO {

    public TLiceDTO podnosilacPrijave;
    public TLiceDTO punomocnik;
    public ZigDTO zig;
    public TakseDTO takse;
    public ZigPrilozi prilozi;
    public String datumPodnosenja;
}