package uol.compass.desafiopb.mspayment.controller;

import org.springframework.http.HttpStatus;
import uol.compass.desafiopb.mspayment.client.CustomerResourceClient;
import uol.compass.desafiopb.mspayment.client.RuleResourceClient;
import uol.compass.desafiopb.mspayment.dto.PaymentRequest;
import uol.compass.desafiopb.mspayment.dto.PaymentResponse;
import uol.compass.desafiopb.mspayment.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mspayment.model.Payment;
import uol.compass.desafiopb.mspayment.service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Data
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final CustomerResourceClient customerClient;
    private final RuleResourceClient ruleClient;

    @PostMapping
    public ResponseEntity<Map<String, Object>> savePayment(@RequestBody PaymentRequest request) {
        ResponseEntity customerResponse = customerClient.getCustomer(request.getCustomerId());
        if (customerResponse.getStatusCode() != HttpStatus.OK){
            throw new ResourceNotFoundException("Customer not found with ID: " + request.getCustomerId());
        }
        ResponseEntity ruleResponse = ruleClient.getRule(request.getRuleId());
        if (ruleResponse.getStatusCode() != HttpStatus.OK){
            throw new ResourceNotFoundException("Rule not found with ID: " + request.getRuleId());
        }
        var payment = request.toPayment();
        paymentService.save(payment);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(payment.getId())
                .toUri();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id: ", payment.getId());
        responseBody.put("created date: ", payment.getCreatedDate());

        return ResponseEntity.created(headerLocation).body(responseBody);
    }

    @GetMapping(params = "id")
    public ResponseEntity getPayment(@RequestParam ("id") String id){
        var payment = paymentService.getByID(id);
        if(payment.isEmpty()){
            throw new ResourceNotFoundException("Payment with ID " + id + "not found");
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping(value = "/customer-id", params = "id")
    public ResponseEntity getPaymentByCustomerId(@RequestParam ("id") String customerId){
        List<Payment> list = paymentService.listPaymentsByCustomerId(customerId);
        List<PaymentResponse> resultList = list.stream()
                .map((Payment model) -> PaymentResponse.fromModel(model))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
