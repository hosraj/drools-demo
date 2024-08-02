package com.wasl.drooldemo.controller;

import com.wasl.drooldemo.entity.CompanyTable;
import com.wasl.drooldemo.entity.Table;
import com.wasl.drooldemo.service.CompanyTableService;
import com.wasl.drooldemo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;


    @Autowired
    private CompanyTableService companyTableService;


    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        return ResponseEntity.ok(tableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Table> getTableById(@PathVariable String id) {
        Optional<Table> table = tableService.findById(id);
        return table.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        return ResponseEntity.ok(tableService.save(table));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable String id, @RequestBody Table table) {
        if (!tableService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        table.setId(id);
        return ResponseEntity.ok(tableService.save(table));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        tableService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-and-store")
    public ResponseEntity<String> validateAndStoreArray(@RequestParam String tableName, @RequestBody Object array) {
        Optional<Table> tableOpt = tableService.findByName(tableName);
        if (tableOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Table table = tableOpt.get();
        List<Integer> size = table.getSize();

        List<Integer> flattenedArray = new ArrayList<>();
        if (!tableService.validateAndFlattenArray(array, size, flattenedArray)) {
            return ResponseEntity.badRequest().body("Invalid array size");
        }

        CompanyTable companyTable = new CompanyTable();
        companyTable.setContent(flattenedArray);

        companyTableService.save(companyTable);

        return ResponseEntity.ok("Array validated and stored successfully");
    }
}
