package com.salesforce.de.dg.heroku.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.model.entity.Account;
import com.salesforce.de.dg.heroku.repository.AccountRepo;

@Profile(value="herokuconnect-cloud")
@Repository
@RestResource(rel = "accounts", path = "accounts")
public interface AccountRepoJPA  extends JpaRepository<Account, Integer>, AccountRepo{

}
