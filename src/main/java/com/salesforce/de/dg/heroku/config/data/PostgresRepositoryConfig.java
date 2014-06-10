package com.salesforce.de.dg.heroku.config.data;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy
@Profile("postgres")
@EnableJpaRepositories("com.salesforce.de.dg.heroku.repository.jpa")
public class PostgresRepositoryConfig extends AbstractJpaRepositoryConfig {

    protected String getHibernateDialect() {
        return PostgreSQL82Dialect.class.getName();
    }

}