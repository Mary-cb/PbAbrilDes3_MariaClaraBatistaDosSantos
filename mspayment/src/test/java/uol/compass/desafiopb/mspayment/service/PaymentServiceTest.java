package uol.compass.desafiopb.mspayment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uol.compass.desafiopb.mspayment.exception.InvalidRequestException;
import uol.compass.desafiopb.mspayment.model.Payment;
import uol.compass.desafiopb.mspayment.repository.PaymentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static uol.compass.desafiopb.mspayment.constant.PaymentConstant.INVALID_PAYMENT;
import static uol.compass.desafiopb.mspayment.constant.PaymentConstant.PAYMENT;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void savePaymentWithValidData(){
        when(paymentRepository.save(PAYMENT)).thenReturn(PAYMENT);

        Payment sut = paymentService.save(PAYMENT);

        assertThat(sut).isEqualTo(PAYMENT);
    }

    @Test
    public void savePaymentWithInvalidData(){
        when(paymentRepository.save(INVALID_PAYMENT)).thenThrow(InvalidRequestException.class);

        assertThatThrownBy(() -> paymentService.save(INVALID_PAYMENT)).isInstanceOf(InvalidRequestException.class);

    }

    @Test
    public void getPaymentWithExistingId(){
        when(paymentRepository.findById("1")).thenReturn(Optional.of(PAYMENT));

        Optional<Payment> sut = paymentService.getByID("1");

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PAYMENT);
    }

    @Test
    public void getPaymentWithUnexistingId(){
        when(paymentRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Payment> sut = paymentService.getByID("1");

        assertThat(sut).isEmpty();
    }

    @Test
    public void getPaymentByCustomerWithExistingId() {
        List<Payment> expectedPayments = Arrays.asList(PAYMENT);

        when(paymentRepository.findByCustomerId("2")).thenReturn(expectedPayments);

        List<Payment> sut = paymentService.listPaymentsByCustomerId("2");

        assertThat(sut).isNotEmpty();
        assertThat(sut).isEqualTo(expectedPayments);
    }

    @Test
    public void getPaymentByCustomerWithUnexistingId() {
        when(paymentRepository.findByCustomerId("nonexistent")).thenReturn(Collections.emptyList());

        List<Payment> sut = paymentService.listPaymentsByCustomerId("nonexistent");

        assertThat(sut).isEmpty();
    }


}