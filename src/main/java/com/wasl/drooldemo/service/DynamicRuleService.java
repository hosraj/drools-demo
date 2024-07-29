package com.wasl.drooldemo.service;

import com.wasl.drooldemo.entity.Employee;
import com.wasl.drooldemo.entity.Rule;
import com.wasl.drooldemo.repository.EmployeeRepository;
import com.wasl.drooldemo.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DynamicRuleService {
    private final RuleRepository ruleRepository;
    private final EmployeeRepository employeeRepository;

    public void addRule(String companyId, String content, LocalDate effectiveDate) {
        Rule rule = new Rule();
        rule.setCompanyId(companyId);
        rule.setContent(content);
        rule.setEffectiveDate(effectiveDate);
        ruleRepository.save(rule);
    }

    public List<Employee> checkSalaries(String companyId, YearMonth month) {
        LocalDate endOfMonth = month.atEndOfMonth();
        List<Rule> rules = ruleRepository.findByCompanyIdAndEffectiveDateLessThanEqualOrderByEffectiveDateDesc(companyId, endOfMonth);
        if (rules.isEmpty()) {
            throw new RuntimeException("Rules not found for company: " + companyId);
        }
        String drl = rules.get(0).getContent();

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceFactory.newByteArrayResource(drl.getBytes()));
        KieBase kieBase = kieHelper.build();

        KieSession kieSession = kieBase.newKieSession();

        List<Employee> employees = employeeRepository.findByCompanyId(companyId);
        for (Employee employee : employees) {
            kieSession.insert(employee);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        employeeRepository.saveAll(employees);
        return employees;
    }
}
