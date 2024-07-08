package uol.compass.desafiopb.mspayment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mscalculate", url = "http://localhost:8081", path = "/v1/rules")
public interface RuleResourceClient {

    @GetMapping(params = "id")
    ResponseEntity getRule(@RequestParam("id") String id);
}