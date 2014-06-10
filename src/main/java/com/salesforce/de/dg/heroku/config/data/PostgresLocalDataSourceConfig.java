package com.salesforce.de.dg.heroku.config.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@Profile("postgres-local")
@PropertySource(value = { "classpath:application.properties" })
public class PostgresLocalDataSourceConfig extends AbstractLocalDataSourceConfig {

	@Autowired
	private Environment env;
	
    @Bean
    public DataSource dataSource() {
        return createBasicDataSource(env.getProperty("jdbc.url"),
                "org.postgresql.Driver", env.getProperty("jdbc.username"), env.getProperty("jdbc.password"));
    }

}