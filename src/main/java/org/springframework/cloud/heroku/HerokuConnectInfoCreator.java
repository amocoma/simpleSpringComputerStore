package org.springframework.cloud.heroku;

import org.springframework.cloud.heroku.HerokuConnector.KeyValuePair;
import org.springframework.cloud.service.common.HerokuConnectInfo;

public class HerokuConnectInfoCreator extends HerokuServiceInfoCreator<HerokuConnectInfo> {

		public HerokuConnectInfoCreator() {
			super("herokuconnect");
		}

		public boolean accept(KeyValuePair serviceData) {
			return serviceData.getKey().toString().startsWith("HEROKUCONNECT_SCHEMA");
		}
		
		@Override
		public HerokuConnectInfo createServiceInfo(String id, String schema) {
			return new HerokuConnectInfo(HerokuUtil.computeServiceName(id), schema);
		}

		
		
	    @Override
	    public String[] getEnvPrefixes() {
	        return new String[]{ "HEROKUCONNECT_SCHEMA"};
	    }
	}
