package com.wasl.drooldemo.controller;


import com.wasl.drooldemo.entity.Employee;
import com.wasl.drooldemo.service.DynamicRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

/**
 * REST controller for managing dynamic rules and checking employee salaries.
 */
@RestController
@RequestMapping("/dynamic-rule")
public class DynamicRuleController {

    @Autowired
    private DynamicRuleService dynamicRuleService;

    /**
     * Adds a new rule for the specified company.
     *
     * @param companyId     the ID of the company
     * @param ruleContent   the content of the rule
     * @param effectiveDate the date from which the rule is effective
     * @return a response entity indicating the outcome of the operation
     */
    @PostMapping("/add")
    public ResponseEntity<Void> addRule(@RequestParam String companyId, @RequestParam String ruleContent, @RequestParam String effectiveDate) {
        dynamicRuleService.addRule(companyId, ruleContent, YearMonth.parse(effectiveDate).atEndOfMonth());
        return ResponseEntity.ok().build();
    }

    /**
     * Checks the salaries of employees for the specified company and month.
     *
     * @param companyId the ID of the company
     * @param month     the month for which to check salaries
     * @return a list of employees with updated salary validation status
     */
    @PostMapping("/check-salary")
    public ResponseEntity<List<Employee>> checkSalary(@RequestParam String companyId, @RequestParam String month) {
        List<Employee> employees = dynamicRuleService.checkSalaries(companyId, YearMonth.parse(month));
        return ResponseEntity.ok(employees);
    }
}
