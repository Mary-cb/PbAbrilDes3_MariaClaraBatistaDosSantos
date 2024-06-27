package io.desafiopb.microservices.mscalculate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "calculate_category")
    private String category;
    @Column(name = "calculate_parity")
    private Integer parity;

    public Rule(String category, Integer parity) {
        this.category = category;
        this.parity = parity;
    }
}
