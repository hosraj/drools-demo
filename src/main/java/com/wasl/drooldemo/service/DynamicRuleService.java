package com.wasl.drooldemo.service;


import com.wasl.drooldemo.entity.Company;
import com.wasl.drooldemo.entity.Employee;
import com.wasl.drooldemo.entity.Rule;
import com.wasl.drooldemo.repository.CompanyRepository;
import com.wasl.drooldemo.repository.EmployeeRepository;
import com.wasl.drooldemo.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

/**
 * Service class for managing dynamic rules and checking employee salaries.
 */
@Service
@RequiredArgsConstructor
public class DynamicRuleService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private KieServices kieServices = KieServices.Factory.get();
    private Map<String, Map<LocalDate, KieContainer>> kieContainers = new HashMap<>();

    /**
     * Adds a new rule for the specified company and updates the rule engine.
     *
     * @param companyId     the ID of the company
     * @param ruleContent   the content of the rule
     * @param effectiveDate the date from which the rule is effective
     */
    public void addRule(String companyId, String ruleContent, LocalDate effectiveDate) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Company not found"));

        Rule rule = new Rule(companyId, ruleContent, effectiveDate);
        ruleRepository.save(rule);

        List<Rule> rules = ruleRepository.findByCompanyId(companyId);
        String drl = rules.stream()
                .map(Rule::getRuleContent)
                .reduce("", (acc, content) -> acc + content);

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        Resource resource = ResourceFactory.newByteArrayResource(drl.getBytes());
        kieFileSystem.write("src/main/resources/" + companyId + "/" + effectiveDate.toString() + "/rules.drl", resource);

        kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieRepository kieRepository = kieServices.getRepository();
        KieContainer kieContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
        Map<LocalDate, KieContainer> localDateKieContainerMap = kieContainers.get(companyId);
        localDateKieContainerMap.put(effectiveDate, kieContainer);
        kieContainers.put(companyId, localDateKieContainerMap);
    }

    /**
     * Checks the salaries of employees for the specified company and month.
     *
     * @param companyId the ID of the company
     * @param month     the month for which to check salaries
     * @return a list of employees with updated salary validation status
     */
    public List<Employee> checkSalaries(String companyId, YearMonth month) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        List<Employee> employees = employeeRepository.findByCompanyIdAndMonth(companyId, month);
        Map<LocalDate, KieContainer> localDateKieContainerMap = kieContainers.get(companyId);
        if (localDateKieContainerMap == null) {
            throw new RuntimeException("Rules not found for company: " + companyId);
        }
        LocalDate lastDayOfMonth = month.atEndOfMonth();

        Optional<LocalDate> maxDateLessThanOrEqualToLastDayOfMonth =
                localDateKieContainerMap.keySet().stream()
                        .filter(date -> !date.isAfter(lastDayOfMonth))
                        .max(Comparator.naturalOrder());

        KieContainer kieContainer = localDateKieContainerMap
                .get(maxDateLessThanOrEqualToLastDayOfMonth);

        if (kieContainer == null) {
            throw new RuntimeException(
                    "Rules not found for company: " + companyId +
                    " for " + maxDateLessThanOrEqualToLastDayOfMonth);
        }

        KieSession kieSession = kieContainer.newKieSession();

        employees.forEach(employee -> kieSession.insert(employee));

        kieSession.fireAllRules();
        kieSession.dispose();

        employeeRepository.saveAll(employees);
        return employees;
    }
    //todo public List<Employee> checkSalaries(List<Employee>) {
    //  // Group employees by YearMonth
    //        Map<YearMonth, List<Employee>> groupedByYearMonth = employees.stream()
    //                .collect(Collectors.groupingBy(Employee::getYearMonth));
    //
    //        // Print the grouped result
    //        groupedByYearMonth.forEach((yearMonth, employeeList) -> {
    //            System.out.println("YearMonth: " + yearMonth);
    //            employeeList.forEach(employee -> System.out.println("    Employee: " + employee));
    //        });
}
