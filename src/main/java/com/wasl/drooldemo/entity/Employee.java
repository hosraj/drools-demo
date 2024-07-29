package com.wasl.drooldemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.YearMonth;

/**
 * Entity representing an employee.
 */
@Data
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;
    private String name;
    private double salary;
    private int yearsOfExperience;
    private String position;
    private String education;
    private String companyId;
    private boolean valid;
    private YearMonth month;

    // Constructor with parameters
    public Employee(String id, int salary, int yearsOfExperience, String position, String education, String companyId, YearMonth employmentDate) {
        this.id = id;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
        this.position = position;
        this.education = education;
        this.companyId = companyId;
        this.month = employmentDate;
    }


}
