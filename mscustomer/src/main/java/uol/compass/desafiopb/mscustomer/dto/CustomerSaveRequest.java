package uol.compass.desafiopb.mscustomer.dto;

import uol.compass.desafiopb.mscustomer.enums.Gender;
import uol.compass.desafiopb.mscustomer.model.Customer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CustomerSaveRequest {
    
    @Size(min = 11, max = 11)
    private String cpf;
    @Size(min = 3)
    private String name;
    private Gender gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    private String email;
    private String photo;

    public Customer toCustomer(){
        return new Customer(cpf, name, gender, birthdate, email, photo);
    }
}
