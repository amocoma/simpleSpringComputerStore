package com.salesforce.de.dg.heroku.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

import com.salesforce.de.dg.heroku.util.treasuredata.TreasureDataCloudLogger;
import com.salesforce.de.dg.heroku.util.treasuredata.TreasureDataLogger;

@Configuration
@EnableAspectJAutoProxy
@Profile({"treasuredata-cloud"})
public class TreasureDataCloudConfig {
	@Bean
    public TreasureDataLogger treasureDataLogger() {
		return new TreasureDataCloudLogger();
	}
}
