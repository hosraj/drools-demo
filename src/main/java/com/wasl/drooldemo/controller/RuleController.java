package com.wasl.drooldemo.controller;

import com.wasl.drooldemo.entity.Rule;
import com.wasl.drooldemo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping
    public ResponseEntity<List<Rule>> getAllRules() {
        return ResponseEntity.ok(ruleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rule> getRuleById(@PathVariable String id) {
        Optional<Rule> rule = ruleService.findById(id);
        return rule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        return ResponseEntity.ok(ruleService.save(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rule> updateRule(@PathVariable String id, @RequestBody Rule rule) {
        if (!ruleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rule.setId(id);
        return ResponseEntity.ok(ruleService.save(rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable String  id) {
        ruleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
