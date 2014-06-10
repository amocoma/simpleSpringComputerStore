package com.salesforce.de.dg.heroku.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.entity.Company;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;

@Repository
public interface CompanyRepoJPA extends JpaRepository<Company, Integer>, CompanyRepo{
} 
