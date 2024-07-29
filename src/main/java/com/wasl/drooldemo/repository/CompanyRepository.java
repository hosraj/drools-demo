package com.wasl.drooldemo.repository;

import com.wasl.drooldemo.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
