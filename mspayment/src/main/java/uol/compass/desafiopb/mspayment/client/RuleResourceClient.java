package uol.compass.desafiopb.mspayment.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient(value = "mscalculate", path = "/v1/rules")
public interface RuleResourceClient {
    @GetMapping(params = "id")
    ResponseEntity getRule(@RequestParam("id") String id);
}
