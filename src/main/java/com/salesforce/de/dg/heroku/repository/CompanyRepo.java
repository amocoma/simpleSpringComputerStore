package com.salesforce.de.dg.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesforce.de.dg.heroku.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer>{
}
