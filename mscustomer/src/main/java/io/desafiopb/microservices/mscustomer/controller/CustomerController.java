package io.desafiopb.microservices.mscustomer.controller;

import io.desafiopb.microservices.mscustomer.dto.CustomerSaveRequest;
import io.desafiopb.microservices.mscustomer.model.Customer;
import io.desafiopb.microservices.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveCustomer(@RequestBody CustomerSaveRequest request) {
        var customer = request.toCustomer();
        customerService.save(customer);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(customer.getId())
                .toUri();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", customer.getId());

        return ResponseEntity.created(headerLocation).body(responseBody);
    }

    @GetMapping(params = "id")
    public ResponseEntity getCustomer(@RequestParam ("id") String id){
        var customer = customerService.getByID(id);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteCustomer(@RequestParam("id") String id) {
        customerService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(params = "id")
    public ResponseEntity<Customer> updateCustomer(@RequestParam("id") String id, @RequestBody CustomerSaveRequest request) {
        var existingCustomer = customerService.findById(id);
        if (existingCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        var updatedCustomer = request.toCustomer();
        updatedCustomer.setId(id);
        customerService.save(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping
    private String status(){
        return "ok";
    }
}
