package io.desafiopb.microservices.mscustomer.model;

import io.desafiopb.microservices.mscustomer.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "customer_cpf")
    private String cpf;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_gender")
    private Gender gender;
    @Column(name = "customer_birthdate")
    private LocalDate birthdate;
    @Column(name = "customer_email")
    private String email;
    @Column(name = "customer_points")
    private Integer points = 0;
    @Column(name = "customer_photo")
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
