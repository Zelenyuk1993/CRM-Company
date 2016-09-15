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

    private Integer profit;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "parentCompanies")
    private Set<Company> childrenCompanies = new HashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
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

    public void setProfit(Integer profit) {
        this.profit = profit;
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
}
