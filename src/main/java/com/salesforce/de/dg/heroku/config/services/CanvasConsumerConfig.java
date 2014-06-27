package com.salesforce.de.dg.heroku.config.services;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.common.CanvasConsumerInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;


@Configuration
@EnableAspectJAutoProxy
@Profile({"canvasconsumer"})
public class CanvasConsumerConfig extends AbstractCloudConfig{
	
	@Bean
	public CanvasConsumerInfo canvasConsumerInfo(){
		return (CanvasConsumerInfo) cloud().getServiceInfo("CANVAS_CONSUMER");
	}
	
}