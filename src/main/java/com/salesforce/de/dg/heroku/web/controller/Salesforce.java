package com.salesforce.de.dg.heroku.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesforce.de.dg.heroku.model.entity.Account;
import com.salesforce.de.dg.heroku.repository.AccountRepo;

@Profile(value="herokuconnect-cloud")
@Controller
@RequestMapping(value = { "/sfdc" })
public class Salesforce {

	@Autowired
	private AccountRepo accRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		AccountForm af = new AccountForm();
		af.setAccounts(accRepo.findAll());
		model.addAttribute("af", af);
		model.addAttribute("accounts", accRepo.findAll());
		return "sfdc/list";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("af") AccountForm af,
			BindingResult result, ModelMap model) {
		accRepo.save(af.getAccounts());
		return "redirect:/sfdc";
	}

	public static class AccountForm {
		public List<Account> accounts;

		public AccountForm() {
			this.accounts = new ArrayList<Account>();
		}

		public List<Account> getAccounts() {
			return accounts;
		}

		public void setAccounts(List<Account> accounts) {
			this.accounts = accounts;
		}

		public void setAccounts(Iterable<Account> accounts) {
			this.accounts = toList(accounts);
		}

		public <E> List<E> toList(Iterable<E> iterable) {
			if (iterable instanceof List) {
				return (List<E>) iterable;
			}
			ArrayList<E> list = new ArrayList<E>();
			if (iterable != null) {
				for (E e : iterable) {
					list.add(e);
				}
			}
			return list;
		}
	}
}
