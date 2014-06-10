package com.salesforce.de.dg.heroku.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesforce.de.dg.heroku.entity.Computer;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;
 
@Repository
public interface ComputerRepoJPA extends JpaRepository<Computer, Integer>, ComputerRepo{ 

	List<Computer> findByNameLike(String name, Sort sort);
	Page<Computer> findByNameLike(String name, Pageable page);
}
