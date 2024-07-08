package uol.compass.desafiopb.mscalculate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import uol.compass.desafiopb.mscalculate.dto.CalculateRequest;
import uol.compass.desafiopb.mscalculate.dto.CalculateResponse;
import uol.compass.desafiopb.mscalculate.dto.SendPointsRequest;
import uol.compass.desafiopb.mscalculate.exception.InvalidRequestException;
import uol.compass.desafiopb.mscalculate.exception.ResourceNotFoundException;
import uol.compass.desafiopb.mscalculate.model.Rule;
import uol.compass.desafiopb.mscalculate.mqueue.PointsPublisher;
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
    private final PointsPublisher pointsPublisher;

    @PostMapping
    public CalculateResponse calculate(@RequestBody CalculateRequest request) throws JsonProcessingException {
        if (request.getId() == null || request.getId().isEmpty()) {
            return new CalculateResponse(request.getValue());
        }
        if (request.getCustomerId() == null || request.getCustomerId().isEmpty()) {
            throw new InvalidRequestException("Customer ID can not be null or empty.");
        }
        Rule rule = ruleService.getRule(request.getId());
        if (rule == null) {
            throw new ResourceNotFoundException("Rule not found for ruleID: " + request.getId());
        }

        BigDecimal parity = BigDecimal.valueOf(rule.getParity());
        BigDecimal value = request.getValue();
        BigDecimal total = parity.multiply(value);

        SendPointsRequest sendPointsRequest = new SendPointsRequest();
        sendPointsRequest.setId(request.getCustomerId());
        sendPointsRequest.setPoints(total.intValue());

        pointsPublisher.sendPoints(sendPointsRequest);
        return new CalculateResponse(total);
    }
}