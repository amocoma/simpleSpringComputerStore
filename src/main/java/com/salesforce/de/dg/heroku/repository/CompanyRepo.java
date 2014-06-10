package com.salesforce.de.dg.heroku.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesforce.de.dg.heroku.entity.Company;

public interface CompanyRepo extends PagingAndSortingRepository<Company, Integer>{
}