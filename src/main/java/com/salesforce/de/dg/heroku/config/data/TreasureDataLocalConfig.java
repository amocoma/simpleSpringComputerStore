package com.salesforce.de.dg.heroku.config.data;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.salesforce.de.dg.heroku.util.treasuredata.TreasureDataLocalLogger;
import com.salesforce.de.dg.heroku.util.treasuredata.TreasureDataLogger;

@Configuration
@EnableAspectJAutoProxy
@Profile({"treasuredata-local"})
@PropertySource(value = { "classpath:application.properties" })
public class TreasureDataLocalConfig {
	
	@Autowired	
	Environment env;
	
	@Bean
    public TreasureDataLogger treasureDataLogger() {
		Properties prop = new Properties();
		prop.put("???", env.getProperty("treasureData.apiKey"));
		return new TreasureDataLocalLogger(env.getProperty("treasureData.database"), prop);
	}
}
