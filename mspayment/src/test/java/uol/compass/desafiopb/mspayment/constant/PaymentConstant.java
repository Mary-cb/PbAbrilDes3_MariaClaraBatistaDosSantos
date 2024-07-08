package uol.compass.desafiopb.mspayment.constant;

import uol.compass.desafiopb.mspayment.model.Payment;

import java.math.BigDecimal;

public class PaymentConstant {
    public static final Payment PAYMENT = new Payment("1", "2", new BigDecimal("100.00"));
    public static final Payment INVALID_PAYMENT = new Payment("", "", new BigDecimal("100.00"));
}
