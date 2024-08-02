package com.wasl.drooldemo.repository;


import com.wasl.drooldemo.entity.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing Rule entities.
 */
public interface RuleRepository extends MongoRepository<Rule, String> {

    /**
     * Finds rules by company ID.
     *
     * @param companyId the ID of the company
     * @return a list of rules for the specified company
     */
    List<Rule> findByCompanyId(String companyId);
}
