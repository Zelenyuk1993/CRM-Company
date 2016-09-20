package com.crm.models;

import com.crm.config.JacksonCompanyDeserializer;
import com.crm.config.JacksonCompanySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Company Entity
 *
 * @author Dima Zelenyuk
 */
@Entity
@JsonSerialize(using = JacksonCompanySerializer.class)
@JsonDeserialize(using = JacksonCompanyDeserializer.class)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private Integer profit = 0;

    private Integer sum = 0;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REMOVE}, mappedBy = "parentCompanies")
    private Set<Company> childrenCompanies = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Company parentCompanies;

    public Company(){
    }

    public Company(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfit() {
        return profit;
    }

    public void sum(){
        addProfit(this.parentCompanies);
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
        addProfit(this.parentCompanies);
    }

    public void setProfitForSerealization(Integer profit){
        this.profit = profit;
    }

    private void addProfit(Company company) {
        if(company != null){
            System.err.println("aa");
            company.addSum(this.sum);
            company.addSum(this.profit);
            addProfit(company.getParentCompanies());
        }else {
            addSum(getProfit());
        }
    }

    public Integer getSum() {
        return sum;
    }

    public void addSum(Integer sum){
        this.sum = sum + this.sum;
    }

    public Set<Company> getChildrenCompanies() {
        return childrenCompanies;
    }

    public void setChildrenCompanies(Set<Company> childrenCompanies) {
        this.childrenCompanies = childrenCompanies;
    }

    public void addChildren(Company children){
        this.childrenCompanies.add(children);
    }

    public Company getParentCompanies() {
        return parentCompanies;
    }

    public void setParentCompanies(Company parentCompanies) {
        this.parentCompanies = parentCompanies;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
