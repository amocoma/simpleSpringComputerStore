package com.salesforce.de.dg.heroku.util.treasuredata;

import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TreasureDataLocalLogger implements TreasureDataLogger{

	private ObjectMapper om = new ObjectMapper();
	private static final String prefix = "local";
	
	public TreasureDataLocalLogger(String database, Properties prop){
		// instantiate TDLogger
	}
	
	@Override
	public void log(String table, Object o) {
		if(o!=null){
			try {
				String jsonStr = "@" + prefix + "." + table +"] " + om.writeValueAsString(o);
				// log to TDLogger
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

	}

}
