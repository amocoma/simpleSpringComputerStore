package com.salesforce.de.dg.heroku.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesforce.de.dg.heroku.entity.Computer;

public interface ComputerRepo extends JpaRepository<Computer, Long> {

	List<Computer> findByNameLike(String name, Sort sort);
	Page<Computer> findByNameLike(String name, Pageable page);
}
