package uol.compass.desafiopb.mscalculate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRequest {
    private String id;
    private BigDecimal value;

    public CalculateRequest toCalculate(){
        return new CalculateRequest(id, value);
    }
}
