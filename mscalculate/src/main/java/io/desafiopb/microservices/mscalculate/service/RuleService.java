package io.desafiopb.microservices.mscalculate.service;

import io.desafiopb.microservices.mscalculate.model.Rule;
import io.desafiopb.microservices.mscalculate.repository.CalculateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleService {
    private final CalculateRepository calculateRepository;

    public Rule saveRule(Rule calculateRule){
        return calculateRepository.save(calculateRule);
    }

    public Optional<Rule> getByID(String id){
        return calculateRepository.findById(id);
    }
}
