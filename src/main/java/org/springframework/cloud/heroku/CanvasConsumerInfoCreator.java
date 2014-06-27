package org.springframework.cloud.heroku;

import java.io.IOException;

import org.springframework.cloud.heroku.HerokuConnector.KeyValuePair;
import org.springframework.cloud.service.common.CanvasConsumerInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.salesforce.de.dg.heroku.model.canvas.CanvasConsumer;

public class CanvasConsumerInfoCreator extends HerokuServiceInfoCreator<CanvasConsumerInfo> {

		public CanvasConsumerInfoCreator() {
			super("canvasconsumer");
		}

		public boolean accept(KeyValuePair serviceData) {
			return serviceData.getKey().toString().equals("CANVAS_CONSUMER");
		}
		
		@Override
		public CanvasConsumerInfo createServiceInfo(String id, String info) {
	        ObjectMapper mapper = new ObjectMapper();
	        ObjectReader reader = mapper.reader(CanvasConsumer.class);
	        try {
				return new CanvasConsumerInfo(HerokuUtil.computeServiceName(id), (CanvasConsumer)reader.readValue(info));
	        } catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	    @Override
	    public String[] getEnvPrefixes() {
	        return new String[]{ "CANVAS_CONSUMER"};
	    }
	}
