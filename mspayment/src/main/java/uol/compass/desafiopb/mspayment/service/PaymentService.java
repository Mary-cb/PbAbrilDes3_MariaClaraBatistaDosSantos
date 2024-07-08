package uol.compass.desafiopb.mspayment.service;

import uol.compass.desafiopb.mspayment.model.Payment;
import uol.compass.desafiopb.mspayment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

    @Transactional
    public Optional<Payment> getByID(String id){
        return paymentRepository.findById(id);
    }

    @Transactional
    public List<Payment> listPaymentsByCustomerId(String customerId){
        return paymentRepository.findByCustomerId(customerId);}
}
