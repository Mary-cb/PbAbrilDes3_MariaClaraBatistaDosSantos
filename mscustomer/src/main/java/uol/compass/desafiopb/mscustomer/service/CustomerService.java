package uol.compass.desafiopb.mscustomer.service;

import uol.compass.desafiopb.mscustomer.model.Customer;
import uol.compass.desafiopb.mscustomer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Optional<Customer> getByID(String id){
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer update(Customer customer) { return customerRepository.save(customer);}

}
