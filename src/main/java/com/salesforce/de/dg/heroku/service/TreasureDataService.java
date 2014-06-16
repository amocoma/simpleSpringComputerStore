package com.salesforce.de.dg.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.de.dg.heroku.util.treasuredata.TreasureDataLogger;


@Service
public class TreasureDataService {

	@Autowired (required = false)
	private TreasureDataLogger tdLogger;
		
	public void log(String table, Object o) {
		if(this.tdLogger != null){
			tdLogger.log(table, o);
		}
    }
	
}
