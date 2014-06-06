package com.salesforce.de.dg.heroku.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesforce.de.dg.heroku.entity.Company;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;
import com.salesforce.de.dg.heroku.service.CompanyService;
import com.salesforce.de.dg.heroku.service.ComputerService;

@Controller
@RequestMapping(value={"/computers","/computers/*"})
public class Computer {

	@Autowired
	ComputerRepo computerRepo;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model, @RequestParam(value="sort", required=false, defaultValue="name") String sort, @RequestParam(value="query", required=false, defaultValue="") String query) {
		// save new 
		return "redirect:/computers/1?sort="+sort+"&query=" + query;
	}
	
	@RequestMapping(value="/{pageNumber}", method = RequestMethod.GET)
	public String list(@PathVariable Integer pageNumber, @RequestParam(value="sort", required=false, defaultValue="name") String sort, @RequestParam(value="query", required=false, defaultValue="") String query, ModelMap model) {
		if(pageNumber < 1){
			pageNumber = 1;
		};
		Page<com.salesforce.de.dg.heroku.entity.Computer> page;
		if(query == null || query.trim().equals("")){
			query = "";
			page = computerService.getComputers(pageNumber, sort);
		}else{
			page = computerService.getComputers(pageNumber, sort, query);
		}
	    int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());

	    model.addAttribute("sort", sort);
	    model.addAttribute("query", query);
	    model.addAttribute("computerPage", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end); 
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("found", page.getTotalElements());
	    model.addAttribute("computers", page.getContent());
	    
	    return "computer/list";
	}
	
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String newComputer(ModelMap model) {
		com.salesforce.de.dg.heroku.entity.Computer c = new com.salesforce.de.dg.heroku.entity.Computer();
		c.setCompany(new Company());
		model.addAttribute("computer", c);
		model.addAttribute("companies", companyService.options());
		return "computer/new";
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String saveNew(@Valid com.salesforce.de.dg.heroku.entity.Computer computer, BindingResult result, ModelMap model) {
       if(result.hasErrors()) {
    	   model.addAttribute("computer", computer);
    	   model.addAttribute("companies", companyService.options());
    	   return "computer/edit";
        }
		computer.company = companyRepo.findOne(computer.company.getId());
		computerRepo.save(computer);
		model.addAttribute("computer", computer);
		return "computer/view";
	}

	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		model.addAttribute("computer", computerRepo.findOne(id));
		return "computer/view";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		model.addAttribute("computer", computerRepo.findOne(id));
		model.addAttribute("companies", companyService.options());
		return "computer/edit";
	}
	//@ModelAttribute com.salesforce.de.dg.heroku.entity.Computer computer
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@Valid com.salesforce.de.dg.heroku.entity.Computer computer, BindingResult result, ModelMap model) {
	       if(result.hasErrors()) {
	    	   computer.name = "check";
	    	   model.addAttribute("computer", computer);
	    	   model.addAttribute("companies", companyService.options());
	    	   return "computer/edit";
	        }
			computer.company = companyRepo.findOne(computer.company.getId());
			computerRepo.save(computer);
			model.addAttribute("computer", computer);
		return "computer/view";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id) {
		return "computer/list";
	}
	
	
}
