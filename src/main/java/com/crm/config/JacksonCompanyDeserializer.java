package com.crm.config;

import com.crm.models.Company;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * JacksonUserDeserializer
 * Custom JSON deserializer for User json deserialization
 *
 * @author  Andrii Blyznuk
 */
public class JacksonCompanyDeserializer extends JsonDeserializer<Company> {


    @Override
    public Company deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Company company = new Company();
        JsonNode jsonNode = p.getCodec().readTree(p);
        Optional.ofNullable(jsonNode.get("id")).ifPresent(id -> company.setId(id.asLong()));
        Optional.ofNullable(jsonNode.get("name")).ifPresent(name -> company.setName(name.asText()));

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

        Optional.ofNullable(jsonNode.get("parentCompanies")).ifPresent(parentCompaniesJsonNodes -> {
            Set<Company> parentCompanies = new HashSet<>();
            parentCompaniesJsonNodes.forEach(parentCompanyJsonNodes -> {
                Company parentCompany = new Company();
                parentCompany.setId(parentCompanyJsonNodes.get("id").asLong());
                parentCompany.setName(parentCompanyJsonNodes.get("name").asText());
                parentCompanies.add(parentCompany);
            });
            company.setParentCompanies(parentCompanies);
        });

        return company;
    }
}