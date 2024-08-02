
package com.wasl.drooldemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.wasl.drooldemo.entity.CompanyTable;

public interface CompanyTableRepository extends MongoRepository<CompanyTable, Long> {
}
