package uol.compass.desafiopb.mspayment.dto;

import uol.compass.desafiopb.mspayment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String customerId;
    private String ruleId;
    private BigDecimal total;
    private LocalDateTime createdDate;

    public static PaymentResponse fromModel(Payment model){
        return new PaymentResponse(
                model.getCustomerId(),
                model.getRuleId(),
                model.getTotal(),
                model.getCreatedDate()
        );
    }
}
