package com.crm.config;

import com.crm.models.Company;
import com.crm.services.CompanyService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * JacksonCompanyDeserializer
 * Custom JSON deserializer for Company json deserialization
 *
 * @author  Dima Zelenyuk
 */
public class JacksonCompanyDeserializer extends JsonDeserializer<Company> {


    @Override
    public Company deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Company company = new Company();
        JsonNode jsonNode = p.getCodec().readTree(p);
        Optional.ofNullable(jsonNode.get("id")).ifPresent(id -> company.setId(id.asLong()));
        Optional.ofNullable(jsonNode.get("name")).ifPresent(name -> company.setName(name.asText()));

        if (!jsonNode.get("parentCompanies").isNull()) {
                Optional.ofNullable(jsonNode.get("parentCompanies")).ifPresent(parentCompanyJsonNodes -> {
                    Company parentCompany = new Company();
                    parentCompany.setId(parentCompanyJsonNodes.get("id").asLong());
                    parentCompany.setName(parentCompanyJsonNodes.get("name").asText());
                    Optional.ofNullable(parentCompanyJsonNodes.get("sum")).ifPresent(profit -> parentCompany.setSum(profit.asInt()));
                    Optional.ofNullable(parentCompanyJsonNodes.get("profit")).ifPresent(profit -> parentCompany.setProfitForSerealization(profit.asInt()));
                    company.setParentCompanies(parentCompany);
                });
            }



        Optional.ofNullable(jsonNode.get("sum")).ifPresent(sum -> company.setSum(sum.asInt()));
        Optional.ofNullable(jsonNode.get("profit")).ifPresent(profit -> company.setProfit(profit.asInt()));

        Optional.ofNullable(jsonNode.get("childrenCompanies")).ifPresent(childrenCompaniesJsonNodes -> {
            Set<Company> childrenCompanies = new HashSet<>();
            childrenCompaniesJsonNodes.forEach(childrenCompanyJsonNodes -> {
                Company childrenCompany = new Company();
                childrenCompany.setId(childrenCompanyJsonNodes.get("id").asLong());
                childrenCompany.setName(childrenCompanyJsonNodes.get("name").asText());

                childrenCompanies.add(childrenCompany);
            });
            company.setChildrenCompanies(childrenCompanies);
        });

        return company;
    }
}