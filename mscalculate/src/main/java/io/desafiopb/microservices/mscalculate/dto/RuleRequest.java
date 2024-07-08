package io.desafiopb.microservices.mscalculate.dto;

import io.desafiopb.microservices.mscalculate.model.Rule;
import lombok.Data;

@Data
public class RuleRequest {
    private String category;
    private Integer parity;

    public Rule toRule(){
        return new Rule(category, parity);
    }
}
