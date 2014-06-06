package com.salesforce.de.dg.heroku.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.salesforce.de.dg.heroku.entity.Company;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepo compRepo;
	private LinkedHashMap<String,String> options;
	
	public Map<String,String> options() {
		if(this.options == null){
			List<Company> companies = compRepo.findAll(new Sort(Sort.Direction.ASC,"name"));
	        this.options = new LinkedHashMap<String,String>();
	        for(Company c: companies) {
	            options.put(c.getId().toString(), c.getName());
	        }
		}
        return this.options;
    }
	
}
