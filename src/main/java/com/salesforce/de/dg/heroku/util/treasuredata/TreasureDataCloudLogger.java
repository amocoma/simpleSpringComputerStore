package com.salesforce.de.dg.heroku.util.treasuredata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TreasureDataCloudLogger implements TreasureDataLogger{

	private ObjectMapper om = new ObjectMapper();
	private static final String prefix = "cloud";
	
	@Override
	public void log(String table, Object o) {
		if(o!=null){
			try {
				System.out.println("@[" + prefix + "." + table +"] "  + om.writeValueAsString(o));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
