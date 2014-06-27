package com.salesforce.de.dg.heroku.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import com.salesforce.de.dg.heroku.model.entity.Account;

@Profile(value="herokuconnect-cloud")
public interface AccountRepo extends CrudRepository<Account, Integer>{	
}