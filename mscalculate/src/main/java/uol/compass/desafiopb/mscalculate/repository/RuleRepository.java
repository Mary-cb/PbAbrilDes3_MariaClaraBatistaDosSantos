package uol.compass.desafiopb.mscalculate.repository;

import org.springframework.stereotype.Repository;
import uol.compass.desafiopb.mscalculate.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, String> {Rule save(Rule rule);}
