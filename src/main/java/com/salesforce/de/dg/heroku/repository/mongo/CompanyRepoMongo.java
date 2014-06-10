package com.salesforce.de.dg.heroku.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.entity.Company;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;

@Repository
public interface CompanyRepoMongo<A extends CompanyRepo> extends MongoRepository<Company, String> {
 
}
