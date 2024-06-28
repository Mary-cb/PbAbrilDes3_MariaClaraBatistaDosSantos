package io.desafiopb.microservices.mscalculate.dto;


import lombok.Data;

@Data
public class CalculateRequest {
    private String ruleID;
    private Integer value;
}
