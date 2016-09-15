package com.crm.demo;

import com.crm.models.Company;
import com.crm.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by Dimas on 14.09.2016.
 */
@RestController
public class Demo {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/demo")
    public String demo(){

        Company company0 = new Company("Parent1");
        company0.setProfit(1000);
        companyService.save(company0);
        Company company1 = new Company("Children1");
        company1.setProfit(1500);
        company1.setParentCompanies(companyService.findOne(1L));
        companyService.save(company1);
        Company company2 = new Company("Children2");
        company2.setProfit(1250);
        company2.setParentCompanies(companyService.findOne(2L));
        companyService.save(company2);
        Company company3 = new Company("Children3");
        company3.setProfit(1750);
        company3.setParentCompanies(companyService.findOne(2L));
        companyService.save(company3);

        return "OKI";
    }

}
