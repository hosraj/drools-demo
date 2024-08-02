
package com.wasl.drooldemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.wasl.drooldemo.entity.Table;

import java.util.Optional;

public interface TableRepository extends MongoRepository<Table, String> {
    Optional<Table> findByName(String name);
}
