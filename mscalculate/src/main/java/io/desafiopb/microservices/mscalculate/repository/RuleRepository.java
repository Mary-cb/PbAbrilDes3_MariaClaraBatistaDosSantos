package io.desafiopb.microservices.mscalculate.repository;

import io.desafiopb.microservices.mscalculate.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, String> {
    Optional<Rule> findByID (String id);
}
