package io.desafiopb.microservices.mscustomer.service;

import io.desafiopb.microservices.mscustomer.model.Customer;
import io.desafiopb.microservices.mscustomer.repository.CustomerRepository;
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

    public Optional<Customer> getByID(String id){
        return customerRepository.findById(id);
    }

}
