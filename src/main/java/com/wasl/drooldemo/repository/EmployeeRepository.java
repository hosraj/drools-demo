
package com.wasl.drooldemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.wasl.drooldemo.entity.Employee;

import java.time.YearMonth;
import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
    List<Employee> findByCompanyIdAndMonth(String companyId, YearMonth month);
}
