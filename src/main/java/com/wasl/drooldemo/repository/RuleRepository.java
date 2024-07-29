package com.wasl.drooldemo.repository;

import com.wasl.drooldemo.entity.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface RuleRepository extends MongoRepository<Rule, String> {
    List<Rule> findByCompanyIdAndEffectiveDateLessThanEqualOrderByEffectiveDateDesc(String companyId, LocalDate date);
}
