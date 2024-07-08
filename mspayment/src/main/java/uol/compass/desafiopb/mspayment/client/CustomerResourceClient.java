package uol.compass.desafiopb.mspayment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mscustomer", url = "http://localhost:8082", path = "/v1/customers")
public interface CustomerResourceClient {
    @GetMapping(params = "id")
    ResponseEntity getCustomer(@RequestParam("id") String id);
}

