package com.salesforce.de.dg.heroku.repository.mongo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.model.entity.Computer;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;

@Repository
public interface ComputerRepoMongo<A extends ComputerRepo> extends MongoRepository<Computer, Integer>{

	List<Computer> findByNameLike(String name, Sort sort);
	Page<Computer> findByNameLike(String name, Pageable page);
}
