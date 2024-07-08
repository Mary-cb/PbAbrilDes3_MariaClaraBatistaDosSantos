package uol.compass.desafiopb.mscustomer.repository;

import uol.compass.desafiopb.mscustomer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer save(Customer customer);}
