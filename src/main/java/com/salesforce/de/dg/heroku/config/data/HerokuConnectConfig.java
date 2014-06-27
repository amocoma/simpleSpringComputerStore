package com.salesforce.de.dg.heroku.config.data;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.common.HerokuConnectInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
@Profile({"herokuconnect-cloud"})
public class HerokuConnectConfig extends AbstractCloudConfig{

	@Bean
	public HerokuConnectInfo herokuConnect(){
		return (HerokuConnectInfo) cloud().getServiceInfo("HEROKUCONNECT_SCHEMA");
	}
	
}
