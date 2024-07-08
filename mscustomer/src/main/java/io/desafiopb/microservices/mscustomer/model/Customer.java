package io.desafiopb.microservices.mscustomer.model;

import io.desafiopb.microservices.mscustomer.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "cpf", nullable = false)
    @Size(min = 11, max = 11, message = "Cpf not valid.")
    private String cpf;
    @Column(name = "name", nullable = false)
    @Size(min = 3, message = "Your name must contain at least 3 characters")
    private String name;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "points")
    private Integer points = 0;
    @Column(name = "photo")
    private String photo;

    public Customer(String cpf, String name, Gender gender, LocalDate birthdate, String email, String photo) {
        this.cpf = cpf;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.photo = photo;
    }

}
