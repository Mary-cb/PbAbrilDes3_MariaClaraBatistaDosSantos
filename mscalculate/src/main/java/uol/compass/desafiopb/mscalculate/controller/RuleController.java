package uol.compass.desafiopb.mscalculate.controller;

import jakarta.validation.Valid;
import uol.compass.desafiopb.mscalculate.dto.RuleRequest;
import uol.compass.desafiopb.mscalculate.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mscalculate.model.Rule;
import uol.compass.desafiopb.mscalculate.repository.RuleRepository;
import uol.compass.desafiopb.mscalculate.service.RuleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/rules")
@Data
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;
    private final RuleRepository ruleRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveRule(@Valid @RequestBody RuleRequest request) {
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
            throw new ResourceNotFoundException("Rule not found with ID: " + id);
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
            throw new ResourceNotFoundException("Rule not found with ID: " + id);
        }
        var updatedRule = request.toRule();
        updatedRule.setId(id);
        ruleService.save(updatedRule);
        return ResponseEntity.ok(updatedRule);
    }

}
