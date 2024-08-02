//package com.wasl.drooldemo;
//
//import com.wasl.drooldemo.entity.Company;
//import com.wasl.drooldemo.entity.Employee;
//import com.wasl.drooldemo.repository.CompanyRepository;
//import com.wasl.drooldemo.repository.EmployeeRepository;
//import com.wasl.drooldemo.repository.RuleRepository;
//import com.wasl.drooldemo.service.DynamicRuleService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.YearMonth;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * Unit tests for the {@link DynamicRuleService} class.
// * This class contains test cases for checking the correct application of dynamic rules
// * and salary calculations.
// */
//@ExtendWith(MockitoExtension.class)
//public class DynamicRuleServiceTest {
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private RuleRepository ruleRepository;
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private KieContainer kieContainer;
//
//    @Mock
//    private KieSession kieSession;
//
//    @InjectMocks
//    private DynamicRuleService dynamicRuleService;
//
//    /**
//     * Tests the {@link DynamicRuleService#checkSalaries(String, YearMonth)} method.
//     * This test verifies that salaries are correctly checked and updated based on the rules.
//     * It also ensures that rules are fired and session resources are properly disposed of.
//     */
//    @Test
//    public void testCheckSalaries() {
////        String companyId = "companyId";
////        YearMonth month = YearMonth.now();
////
////        // Preparing test data
////        Company company = new Company(companyId, "Company Name");
////        Employee employee1 = new Employee("Employee1", 5000, 5, "Developer", "Bachelor", companyId, F.minusMonths(1));
////        Employee employee2 = new Employee("Employee2", 6000, 6, "Manager", "Master", companyId, month.minusMonths(2));
////        List<Employee> employees = Arrays.asList(employee1, employee2);
////
////        // Mocking repository methods
////        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
////        when(employeeRepository.findByCompanyId(companyId)).thenReturn(employees);
////
////        // Mocking KieSession methods
////        when(kieContainer.newKieSession()).thenReturn(kieSession);
////
////        // Mocking the fireAllRules and dispose methods as they are void methods
////        doNothing().when(kieSession).fireAllRules();
////        doNothing().when(kieSession).dispose();
////
////        // Invoking the service method
////        List<Employee> result = dynamicRuleService.checkSalaries(companyId, month);
////
////        // Verifying the results
////        assertNotNull(result, "The result should not be null");
////        assertEquals(2, result.size(), "The number of employees should match the expected count");
////        verify(kieSession, times(1)).fireAllRules();
////        verify(kieSession, times(1)).dispose();
////        verify(employeeRepository, times(1)).saveAll(employees);
//    }
//}
