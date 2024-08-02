package com.wasl.drooldemo.service;

import com.wasl.drooldemo.entity.CompanyTable;
import com.wasl.drooldemo.repository.CompanyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyTableService {

    @Autowired
    private CompanyTableRepository companyTableRepository;

    public List<CompanyTable> findAll() {
        return companyTableRepository.findAll();
    }

    public Optional< CompanyTable> findById(Long id) {
        return companyTableRepository.findById(id);
    }

    public CompanyTable save(CompanyTable companyTable) {
        return companyTableRepository.save(companyTable);
    }

    public void deleteById(Long id) {
        companyTableRepository.deleteById(id);
    }


}
