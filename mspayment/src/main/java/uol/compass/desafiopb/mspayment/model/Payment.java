package uol.compass.desafiopb.mspayment.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "rule_id")
    private String ruleId;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "created_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();

    public Payment(String customerId, String ruleId, BigDecimal total){
        this.customerId = customerId;
        this.ruleId = ruleId;
        this.total = total;
    }
}
