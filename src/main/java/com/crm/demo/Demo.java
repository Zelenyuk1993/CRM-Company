package com.crm.demo;

import com.crm.models.Company;
import com.crm.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Demo Company CRM
 *
 * @author  by Dima Zelenyuk
 */
@RestController
public class Demo {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/demo")
    public String demo(){

        Company company0 = new Company("Company1");
        company0.setProfit(25);
        companyService.save(company0);
        Company company1 = new Company("Company2");
        company1.setParentCompanies(companyService.findOne(1L));
        company1.setProfit(13);
        companyService.save(company1);
        Company company2 = new Company("Company4");
        company2.setParentCompanies(companyService.findOne(1L));
        company2.setProfit(10);
        companyService.save(company2);
        Company company3 = new Company("Company3");
        company3.setParentCompanies(companyService.findOne(2L));
        company3.setProfit(5);
        companyService.save(company3);

        return "OKI";
    }

}
