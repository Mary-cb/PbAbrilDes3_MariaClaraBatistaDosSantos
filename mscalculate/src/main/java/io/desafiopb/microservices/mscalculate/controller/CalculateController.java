package io.desafiopb.microservices.mscalculate.controller;

import io.desafiopb.microservices.mscalculate.dto.CalculateRequest;
import io.desafiopb.microservices.mscalculate.dto.RuleRequest;
import io.desafiopb.microservices.mscalculate.model.Rule;
import io.desafiopb.microservices.mscalculate.repository.RuleRepository;
import io.desafiopb.microservices.mscalculate.service.RuleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/rules")
@Data
@RequiredArgsConstructor
public class CalculateController {

    private final RuleService ruleService;
    private final RuleRepository ruleRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveRule(@RequestBody RuleRequest request) {
        var rule = request.toRule();
        ruleService.save(rule);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(rule.getId())
                .toUri();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id", rule.getId());

        return ResponseEntity.created(headerLocation).body(responseBody);
    }

    @GetMapping(params = "id")
    public ResponseEntity getRule(@RequestParam ("id") String id){
        var rule = ruleService.getByID(id);
        if(rule.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rule);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteRule(@RequestParam("id") String id) {
        ruleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(params = "id")
    public ResponseEntity<Rule> updateRule(@RequestParam("id") String id, @RequestBody RuleRequest request) {
        var existingRule = ruleService.findById(id);
        if (existingRule == null) {
            return ResponseEntity.notFound().build();
        }
        var updatedRule = request.toRule();
        updatedRule.setId(id);
        ruleService.save(updatedRule);
        return ResponseEntity.ok(updatedRule);
    }

}
