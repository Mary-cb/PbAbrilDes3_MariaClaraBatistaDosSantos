package uol.compass.desafiopb.mscustomer.controller;

import jakarta.validation.Valid;
import uol.compass.desafiopb.mscustomer.dto.CustomerSaveRequest;
import uol.compass.desafiopb.mscustomer.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mscustomer.model.Customer;
import uol.compass.desafiopb.mscustomer.service.CustomerService;
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
    public ResponseEntity<Map<String, Object>> saveCustomer(@Valid @RequestBody CustomerSaveRequest request) throws ResourceNotFoundException {
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
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteCustomer(@RequestParam("id") String id) {
        customerService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(params = "id")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestParam("id") String id, @RequestBody CustomerSaveRequest request) throws Exception {
        var existingCustomer = customerService.findById(id);
        if (existingCustomer == null) {
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        }

        var updatedCustomer = request.toCustomer();
        updatedCustomer.setId(id);
        customerService.save(updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
    
}
