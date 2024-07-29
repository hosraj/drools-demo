package com.wasl.drooldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private double salary;
    private int experience;
    private String position;
    private String education;
    @Field("company_id")
    private String companyId;
    @Field("join_date")
    private YearMonth joinDate;
}
