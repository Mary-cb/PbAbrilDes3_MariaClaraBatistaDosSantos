package uol.compass.desafiopb.mspayment.dto;

import uol.compass.desafiopb.mspayment.model.Payment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String customerId;
    private String ruleId;
    private BigDecimal total;

    public Payment toPayment(){
        return new Payment(this.customerId, this.ruleId, this.total);
    }
}
