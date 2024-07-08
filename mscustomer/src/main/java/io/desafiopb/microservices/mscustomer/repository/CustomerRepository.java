package io.desafiopb.microservices.mscustomer.repository;

import io.desafiopb.microservices.mscustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {}
