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
        Company company1 = new Company("Children1");
        Company company2 = new Company("Children2");
        Company company3 = new Company("Children3");
        company1.addParent(company0);
        company1.addChildren(company2);
        company1.addChildren(company3);
        company3.addParent(company1);
        company2.addParent(company1);
        company0.addChildren(company1);
        companyService.save(company0);

        return "OKI";
    }

}
