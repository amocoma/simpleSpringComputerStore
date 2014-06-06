package com.salesforce.de.dg.heroku.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.salesforce.de.dg.heroku.entity.Computer;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;

@Service
@Transactional
public class ComputerService {

	private static final int PAGE_SIZE = 25;
	@Autowired 
	private ComputerRepo computerRepo;

	public Page<Computer> getComputers(Integer pageNumber, String sort) {
        PageRequest pRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, sort);
        return this.computerRepo.findAll(pRequest);
    }

	public Page<Computer> getComputers(Integer pageNumber, String sort, String query) {
		
        PageRequest pRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, sort);
        return this.computerRepo.findByNameLike("%"+query+"%", pRequest);  
    }
	
}
