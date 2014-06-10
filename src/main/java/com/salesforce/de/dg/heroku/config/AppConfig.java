package com.salesforce.de.dg.heroku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.salesforce.de.dg.heroku.config.data.LocalJpaRepositoryConfig;
import com.salesforce.de.dg.heroku.repository.CompanyRepo;

@Configuration
@ComponentScan(
		basePackageClasses = { CompanyRepo.class, LocalJpaRepositoryConfig.class},
		basePackages = { "com.salesforce.de.dg.heroku" }, 
		excludeFilters = {@ComponentScan.Filter(pattern = { "com.salesforce.de.dg.heroku.web.*" }, type = FilterType.REGEX)
		})

public class AppConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

}