package uol.compass.desafiopb.mscustomer.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uol.compass.desafiopb.mscustomer.enums.Gender;
import uol.compass.desafiopb.mscustomer.model.Customer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import uol.compass.desafiopb.mscustomer.validation.GenderValidation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSaveRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Cpf not valid. It should follow the pattern xxx.xxx.xxx-xx")
    private String cpf;

    @NotBlank
    @Size(min = 3, message = "Your name must contain at least 3 characters")
    private String name;

    @GenderValidation
    private Gender gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @NotBlank
    @Email(regexp = "/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i", message = "Email should be valid")
    private String email;

    @NotBlank
    private String photo;

    public Customer toCustomer() {
        return new Customer(cpf, name, gender, birthdate, email, photo);
    }
}
