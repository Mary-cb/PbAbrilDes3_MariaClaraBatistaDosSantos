package io.desafiopb.microservices.mscustomer.dto;

import io.desafiopb.microservices.mscustomer.enums.Gender;
import io.desafiopb.microservices.mscustomer.model.Customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerSaveRequest {
    private String cpf;
    private String name;
    private Gender gender;
    private LocalDate birthdate;
    private String email;
    private String photo;

    public Customer toCustomer(){
        return new Customer(cpf, name, gender, birthdate, email, photo);
    }
}
