package com.salesforce.de.dg.heroku.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.model.entity.Company;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;

@Repository
@RestResource(exported = false, rel = "companies", path = "companies")
public interface CompanyRepoJPA extends JpaRepository<Company, Integer>, CompanyRepo{
} 
