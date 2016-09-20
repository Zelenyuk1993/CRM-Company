package com.crm.controllers;

import com.crm.models.Company;
import com.crm.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/api/company/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable("id") Long id){
        return companyService.findOne(id);
    }

    @RequestMapping(value = "/api/company", method = RequestMethod.POST)
    public List<Company> save(@RequestBody Company company){
      return companyService.addChildren(company);
    }

    @RequestMapping(value = "/api/company", method = RequestMethod.PUT)
    public Company updateCompany(@RequestBody Company company){
        return companyService.save(company);
    }

    @RequestMapping(value = "/api/company/{id}", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable("id") Long id){
       companyService.deleteCompanyById(id);
    }


}
