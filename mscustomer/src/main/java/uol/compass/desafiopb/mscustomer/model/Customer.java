package uol.compass.desafiopb.mscustomer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang.builder.EqualsBuilder;
import uol.compass.desafiopb.mscustomer.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uol.compass.desafiopb.mscustomer.validation.GenderValidation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "cpf", nullable = false, unique = true)
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Cpf not valid. It should follow the pattern xxx.xxx.xxx-xx")
    private String cpf;

    @Column(name = "name", nullable = false)
    @Size(min = 3, message = "Your name must contain at least 3 characters")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @GenderValidation
    private Gender gender;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email(regexp = "/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i", message = "Email should be valid")
    private String email;

    @Column(name = "points")
    private Integer points = 0;

    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;

    public Customer(String cpf, String name, Gender gender, LocalDate birthdate, String email, String photo) {
        this.cpf = cpf;
        this.name = name;
        this.gender = gender;
        this.birthdate = LocalDate.parse(birthdate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        this.email = email;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object obj){
       return EqualsBuilder.reflectionEquals(obj, this);
    }


}
