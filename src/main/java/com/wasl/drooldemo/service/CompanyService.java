package com.wasl.drooldemo.service;

import com.wasl.drooldemo.entity.Company;
import com.wasl.drooldemo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteById(String id) {
        companyRepository.deleteById(id);
    }
}
