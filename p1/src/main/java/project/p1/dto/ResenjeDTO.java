package project.p1.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResenjeDTO {

    public String imeSluzbenika;
    public String prezimeSluzbenika;
    public String referenca;
    public Boolean odobren;
    public String obrazlozenje;
}
