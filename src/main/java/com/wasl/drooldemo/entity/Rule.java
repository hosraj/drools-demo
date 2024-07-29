package com.wasl.drooldemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Entity representing a rule.
 */
@Data
@RequiredArgsConstructor
@Document(collection = "rules")
public class Rule {

    @Id
    private String id;
    private String companyId;

    private String ruleContent;
    private LocalDate effectiveDate;

    /**
     * Constructor with parameters.
     *
     * @param companyId     the ID of the company
     * @param ruleContent   the content of the rule
     * @param effectiveDate the date from which the rule is effective
     */
    public Rule(String companyId, String ruleContent, LocalDate effectiveDate) {
        this.companyId = companyId;
        this.ruleContent = ruleContent;
        this.effectiveDate = effectiveDate;
    }
}
