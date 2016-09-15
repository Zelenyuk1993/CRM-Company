package com.crm.services;

import com.crm.models.Company;
import com.crm.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Company Service
 *
 * @author Dima Zelenyuk
 */

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }

    public Company save(Company company){
       return companyRepository.save(company);
    }

    public Company findOne(Long id){
        return companyRepository.findOne(id);
    }

    public List<Company> addChildren (Company company){
        Company childrenCompany = companyRepository.save(company);
        if(company.getParentCompanies() != null) {
            Company parentCompany = companyRepository.findOne(company.getParentCompanies().getId());
            parentCompany.addChildren(childrenCompany);
            companyRepository.save(parentCompany);
        }
        return companyRepository.findAll();
    }

}
