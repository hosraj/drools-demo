package com.wasl.drooldemo.service;

import com.wasl.drooldemo.entity.Rule;
import com.wasl.drooldemo.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> findById(String id) {
        return ruleRepository.findById(id);
    }

    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

    public void deleteById(String id) {
        ruleRepository.deleteById(id);
    }
}
