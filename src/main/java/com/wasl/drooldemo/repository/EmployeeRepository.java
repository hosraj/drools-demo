package com.wasl.drooldemo.repository;

import com.wasl.drooldemo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByCompanyId(String companyId);
}
