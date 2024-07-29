package com.wasl.drooldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rules")
public class Rule {
    @Id
    private String id;
    
    @Field("company_id")
    private String companyId;
    
    @Field("content")
    private String content;
    
    @Field("effective_date")
    private LocalDate effectiveDate;
}
