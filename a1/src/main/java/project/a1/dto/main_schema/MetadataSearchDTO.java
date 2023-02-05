package project.a1.dto.main_schema;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MetadataSearchDTO {

    public List<String> preds;
    public List<String> values;
    public List<String> operators;
}
