package uol.compass.desafiopb.mscalculate.controller;

import uol.compass.desafiopb.mscalculate.dto.CalculateRequest;
import uol.compass.desafiopb.mscalculate.dto.CalculateResponse;
import uol.compass.desafiopb.mscalculate.model.Rule;
import uol.compass.desafiopb.mscalculate.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/calculate")
@RequiredArgsConstructor
public class CalculateController {


    private final RuleService ruleService;

    @PostMapping
    public Object calculate(@RequestBody CalculateRequest request) {
        if (request.getId() == null || request.getId().isEmpty()){
            return request.getValue();
        }

        Rule rule = ruleService.getRule(request.getId());

        if (rule == null) {
            throw new RuntimeException("Rule not found for ruleID: " + request.getId());
        }

        BigDecimal parity = BigDecimal.valueOf(rule.getParity());
        BigDecimal value = request.getValue();
        BigDecimal total = parity.multiply(value);
        return new CalculateResponse(total);
    }
}