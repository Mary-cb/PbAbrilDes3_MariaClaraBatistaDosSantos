package io.desafiopb.microservices.mscustomer.controller;

import io.desafiopb.microservices.mscustomer.dto.CustomerSaveRequest;
import io.desafiopb.microservices.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequest request){
        var customer = request.toCustomer();
        customerService.save(customer);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}").buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity clientData(@RequestParam ("id") String id){
        var customer = customerService.getByID(id);
        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
}
