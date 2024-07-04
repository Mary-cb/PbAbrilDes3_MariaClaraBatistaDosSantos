package uol.compass.desafiopb.mscalculate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "category")
    private String category;
    @Column(name = "parity")
    private Integer parity;

    public Rule(String category, Integer parity) {
        this.category= category;
        this.parity = parity;
    }
}
