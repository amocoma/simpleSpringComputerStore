package com.salesforce.de.dg.heroku.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.model.entity.Computer;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;
 
@Repository
@RestResource(rel = "computers", path = "computers")
public interface ComputerRepoJPA extends JpaRepository<Computer, Integer>, ComputerRepo{ 

	
	@RestResource(path = "name", rel = "names")
	List<Computer> findByNameLike(@Param("name") String name, Sort sort);
	@RestResource(path = "namePaged", rel = "namesPaged")
	Page<Computer> findByNameLike(@Param("namePaged") String name, Pageable page);
}
