package uol.compass.desafiopb.mscustomer.constant;

import uol.compass.desafiopb.mscustomer.enums.Gender;
import uol.compass.desafiopb.mscustomer.model.Customer;
import java.time.LocalDate;

public class CustomerConstant {
    public static final Customer CUSTOMER = new Customer("01234567890", "name", Gender.F, LocalDate.of(2024, 7, 8), "example@example.com", "https://thumbs.dreamstime.com/z/desenho-do-%C3%ADcone-marcador-de-fotos-perfil-em-tons-cinza-escuros-design-posicionamento-avatar-ilustra%C3%A7%C3%A3o-vetorial-214437757.jpg?w=768");
    public static final Customer INVALID_CUSTOMER = new Customer("", "", Gender.F, LocalDate.of(2024, 7, 8), "", "");
}
