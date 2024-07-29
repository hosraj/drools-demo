package com.wasl.drooldemo;

import com.wasl.drooldemo.entity.Company;
import com.wasl.drooldemo.entity.Employee;
import com.wasl.drooldemo.entity.Rule;
import com.wasl.drooldemo.repository.CompanyRepository;
import com.wasl.drooldemo.repository.EmployeeRepository;
import com.wasl.drooldemo.repository.RuleRepository;
import com.wasl.drooldemo.service.DynamicRuleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DynamicRuleServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RuleRepository ruleRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private KieContainer kieContainer;

    @Mock
    private KieSession kieSession;

    @InjectMocks
    private DynamicRuleService dynamicRuleService;

    @Test
    public void testCheckSalaries() {
        String companyId = "companyId";
        YearMonth month = YearMonth.now();

        Company company = new Company(companyId, "Company Name");
        Employee employee1 = new Employee("Employee1", "Employee 1", 5000, 5, "Developer", "Bachelor", companyId, month.minusMonths(1));
        Employee employee2 = new Employee("Employee2", "Employee 2", 6000, 6, "Manager", "Master", companyId, month.minusMonths(2));
        List<Employee> employees = Arrays.asList(employee1, employee2);
        
        Rule rule = new Rule("1", companyId, "rule content", LocalDate.now().minusMonths(1));

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        when(employeeRepository.findByCompanyId(companyId)).thenReturn(employees);
        when(ruleRepository.findByCompanyIdAndEffectiveDateLessThanEqualOrderByEffectiveDateDesc(eq(companyId), any(LocalDate.class))).thenReturn(Arrays.asList(rule));
        when(kieContainer.newKieSession()).thenReturn(kieSession);

        doNothing().when(kieSession).insert(any(Employee.class));
        doNothing().when(kieSession).fireAllRules();
        doNothing().when(kieSession).dispose();

        List<Employee> result = dynamicRuleService.checkSalaries(companyId, month);

        assertEquals(2, result.size());
        verify(kieSession, times(2)).insert(any(Employee.class));
        verify(kieSession, times(1)).fireAllRules();
        verify(kieSession, times(1)).dispose();
        verify(employeeRepository, times(1)).saveAll(employees);
    }
}
