package uol.compass.desafiopb.mspayment.repository;

import uol.compass.desafiopb.mspayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByCustomerId(String customerId);
}
