package uol.compass.desafiopb.mscalculate.service;

import uol.compass.desafiopb.mscalculate.model.Rule;
import uol.compass.desafiopb.mscalculate.repository.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class RuleService {
    private final RuleRepository ruleRepository;

    @Transactional
    public Rule save(Rule rule){
        System.out.println("Saving rule: " + rule);
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

    @Transactional
    public Rule getRule(String id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rule update(Rule rule) { return ruleRepository.save(rule);}

}
