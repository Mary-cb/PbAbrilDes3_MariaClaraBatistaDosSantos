package uol.compass.desafiopb.mscustomer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uol.compass.desafiopb.mscustomer.exception.InvalidRequestException;
import uol.compass.desafiopb.mscustomer.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mscustomer.model.Customer;
import uol.compass.desafiopb.mscustomer.repository.CustomerRepository;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static uol.compass.desafiopb.mscustomer.constant.CustomerConstant.CUSTOMER;
import static uol.compass.desafiopb.mscustomer.constant.CustomerConstant.INVALID_CUSTOMER;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void saveCustomerWithValidData(){
        when(customerRepository.save(CUSTOMER)).thenReturn(CUSTOMER);

       Customer sut = customerService.save(CUSTOMER);

       assertThat(sut).isEqualTo(CUSTOMER);
    }
    @Test
    public void saveCustomerWithInvalidData(){
        when(customerRepository.save(INVALID_CUSTOMER)).thenThrow(InvalidRequestException.class);

        assertThatThrownBy(() -> customerService.save(INVALID_CUSTOMER)).isInstanceOf(InvalidRequestException.class);

    }
    @Test
    public void getCustomerWithExistingId(){
        when(customerRepository.findById("1")).thenReturn(Optional.of(CUSTOMER));

        Optional<Customer> sut = customerService.getByID("1");

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(CUSTOMER);
    }
    @Test
    public void getCustomerWithUnexistingId(){
        when(customerRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Customer> sut = customerService.getByID("1");

        assertThat(sut).isEmpty();
    }
    @Test
    public void deleteRuleWithExistingId(){
        assertThatCode(() -> customerService.deleteById("1")).doesNotThrowAnyException();
    }
    @Test
    public void deleteRuleWithUnexistingId() {
        doThrow(new ResourceNotFoundException("")).when(customerRepository).deleteById("99");

        assertThatThrownBy(() -> customerService.deleteById("99")).isInstanceOf(ResourceNotFoundException.class);
    }
    @Test
    public void updateRuleWithValidData() {

        when(customerRepository.save(CUSTOMER)).thenReturn(CUSTOMER);

        Customer updatedCustomer = customerService.update(CUSTOMER);

        assertThat(updatedCustomer).isEqualTo(CUSTOMER);
    }
    @Test
    public void updateRuleWithInvalidData() {
        when(customerRepository.save(INVALID_CUSTOMER)).thenThrow(InvalidRequestException.class);

        assertThatThrownBy(() -> customerService.update(INVALID_CUSTOMER)).isInstanceOf(InvalidRequestException.class);
    }
}
