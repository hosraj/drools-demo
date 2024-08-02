package com.wasl.drooldemo.repository;


import com.wasl.drooldemo.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing Company entities.
 */
public interface CompanyRepository extends MongoRepository<Company, String> {
}
