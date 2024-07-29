package com.wasl.drooldemo.repository;


import com.wasl.drooldemo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for managing Employee entities.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    /**
     * Finds employees by company ID.
     *
     * @param companyId the ID of the company
     * @return a list of employees in the specified company
     */
    List<Employee> findByCompanyId(String companyId);
}
