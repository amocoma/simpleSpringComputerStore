package com.salesforce.de.dg.heroku.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Profile;

@Profile(value="herokuconnect-cloud")
@Entity
@Table(name="Account", schema="salesforce")
public class Account {

	@Id
	public Integer id;

	public String sfid;

	public String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}


}
