package io.desafiopb.microservices.mscalculate.service;

import io.desafiopb.microservices.mscalculate.model.Rule;
import io.desafiopb.microservices.mscalculate.repository.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class RuleService {
    private final RuleRepository ruleRepository;

    @Transactional
    public Rule save(Rule rule){
        return ruleRepository.save(rule);
    }

    @Transactional
    public void deleteById(String id) {
        ruleRepository.deleteById(id);
    }

    @Transactional
    public Rule findById(String id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Optional<Rule> getByID(String id){
        return ruleRepository.findById(id);
    }


}
