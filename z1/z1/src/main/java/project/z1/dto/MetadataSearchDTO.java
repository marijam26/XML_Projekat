package project.z1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@XmlRootElement
@AllArgsConstructor
public class MetadataSearchDTO {

    public List<String> preds = new ArrayList<>();
    public List<String> values = new ArrayList<>();
    public List<String> operators = new ArrayList<>();

    public MetadataSearchDTO(){}

}
