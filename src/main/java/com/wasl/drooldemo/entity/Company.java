package com.wasl.drooldemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity representing a company.
 */
@Data
@AllArgsConstructor
@Document(collection = "companies")
public class Company {

    @Id
    private String id;
    private String name;
}
