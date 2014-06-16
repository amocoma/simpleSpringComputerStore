package org.springframework.cloud.heroku;

import org.springframework.cloud.heroku.HerokuConnector.KeyValuePair;
import org.springframework.cloud.service.common.TreasureDataInfo;

public class TreasureDataServiceInfoCreator extends HerokuServiceInfoCreator<TreasureDataInfo> {

		public TreasureDataServiceInfoCreator() {
			super("trasuredata");
		}

		public boolean accept(KeyValuePair serviceData) {
			return serviceData.getKey().toString().startsWith("TREASURE_DATA_API_KEY");
		}
		
		@Override
		public TreasureDataInfo createServiceInfo(String id, String uri) {
			return new TreasureDataInfo(HerokuUtil.computeServiceName(id));
		}

		
		
	    @Override
	    public String[] getEnvPrefixes() {
	        return new String[]{ "TREASURE_DATA_API_KEY"};
	    }
	}
