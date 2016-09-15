package com.crm.controllers;

import com.crm.models.Company;
import com.crm.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Company Controller
 *
 * @author Dima Zelenyuk
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/api/companies", method = RequestMethod.GET)
    public List<Company> getCompanies(){
        return companyService.getAll();
    }

    @RequestMapping(value = "/api/company", method = RequestMethod.POST)
    public Company save(@RequestBody Company company){
        return companyService.save(company);
    }



}
