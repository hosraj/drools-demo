package com.wasl.drooldemo.controller;

import com.wasl.drooldemo.entity.CompanyTable;
import com.wasl.drooldemo.service.CompanyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company-table")
public class CompanyTableController {

    @Autowired
    private CompanyTableService companyTableService;

    @GetMapping
    public ResponseEntity<List<CompanyTable>> getAllCompanyTables() {
        return ResponseEntity.ok(companyTableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyTable> getCompanyTableById(@PathVariable Long id) {
        Optional<CompanyTable> companyTable = companyTableService.findById(id);
        return companyTable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompanyTable> createCompanyTable(@RequestBody CompanyTable companyTable) {
        return ResponseEntity.ok(companyTableService.save(companyTable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyTable> updateCompanyTable(@PathVariable Long id, @RequestBody CompanyTable companyTable) {
        if (!companyTableService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        companyTable.setId(id);
        return ResponseEntity.ok(companyTableService.save(companyTable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyTable(@PathVariable Long id) {
        companyTableService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
