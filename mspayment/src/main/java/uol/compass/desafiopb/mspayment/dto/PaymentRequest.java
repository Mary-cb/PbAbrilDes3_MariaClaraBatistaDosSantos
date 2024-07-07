package uol.compass.desafiopb.mspayment.dto;

import uol.compass.desafiopb.mspayment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String customerId;
    private String ruleId;
    private BigDecimal total;

    public Payment toPayment(){
        return new Payment(customerId, ruleId, total);
    }
}
