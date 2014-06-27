package org.springframework.cloud.service.common;

import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

@ServiceLabel("herokuconnect")
public class HerokuConnectInfo extends BaseServiceInfo{

	private final String schema;
	
	public HerokuConnectInfo(String id, String schema) {
		super(id);
		this.schema = schema;
	}

	public String getSchema() {
		return schema;
	}
	
}
