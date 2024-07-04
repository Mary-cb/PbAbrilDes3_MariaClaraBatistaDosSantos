package uol.compass.desafiopb.mscalculate.dto;

import uol.compass.desafiopb.mscalculate.model.Rule;
import lombok.Data;

@Data
public class RuleRequest {
    private String category;
    private Integer parity;

    public Rule toRule(){
        return new Rule(category, parity);
    }
}
