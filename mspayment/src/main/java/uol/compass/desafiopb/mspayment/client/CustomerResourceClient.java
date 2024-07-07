package uol.compass.desafiopb.mspayment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomer", path = "/v1/customers")
public interface CustomerResourceClient {
    @GetMapping(params = "id")
    ResponseEntity getCustomer(@RequestParam ("id") String id);
}
