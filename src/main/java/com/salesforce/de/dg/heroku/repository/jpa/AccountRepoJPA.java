package com.salesforce.de.dg.heroku.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.entity.Account;
import com.salesforce.de.dg.heroku.repository.AccountRepo;

// @Repository
public interface AccountRepoJPA extends JpaRepository<Account, Integer>, AccountRepo{

}
