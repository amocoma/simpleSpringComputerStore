package com.salesforce.de.dg.heroku.config.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration 
@Profile("mysql-local") 

public class MySqlLocalDataSourceConfig extends AbstractLocalDataSourceConfig {

	@Autowired
	private Environment env;
	
    @Bean
    public DataSource dataSource() {
        return createBasicDataSource(env.getProperty("jdbc.url"),
        		"com.mysql.jdbc.Driver", env.getProperty("jdbc.username"), env.getProperty("jdbc.password"));
    }

}
