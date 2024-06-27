package io.desafiopb.microservices.mscalculate.controller;

import io.desafiopb.microservices.mscalculate.dto.RuleRequest;
import io.desafiopb.microservices.mscalculate.service.RuleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1")
@Data
@RequiredArgsConstructor
public class CalculateController {
    private final RuleService calculateService;

    @PostMapping(value = "/rules")
    public ResponseEntity saveRule(@RequestBody RuleRequest request){
        var rule = request.toRule();
        calculateService.saveRule(rule);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}").buildAndExpand(rule.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }



}
