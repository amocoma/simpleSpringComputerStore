package com.salesforce.de.dg.heroku.repository;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.de.dg.heroku.entity.Company;
import com.salesforce.de.dg.heroku.entity.Computer;

@Component
public class DataPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware  {

    private final Jackson2ResourceReader resourceReader;
    private final Resource companyData;
    private final Resource computerData;
    private ApplicationContext applicationContext;
    
    public DataPopulator() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.resourceReader = new Jackson2ResourceReader(mapper);
        this.companyData = new ClassPathResource("companies.json");
        this.computerData = new ClassPathResource("computers.json");
        
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().equals(this.applicationContext)) {
            CompanyRepo companyRepository =
                    BeanFactoryUtils.beanOfTypeIncludingAncestors(this.applicationContext, CompanyRepo.class);

            if (companyRepository != null && companyRepository.count() == 0) {
                populate(companyRepository);
            }
            ComputerRepo computerRepository =
                    BeanFactoryUtils.beanOfTypeIncludingAncestors(this.applicationContext, ComputerRepo.class);

            if (computerRepository != null && computerRepository.count() == 0) {
                populate(computerRepository);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void populate(CompanyRepo repository) {
        Object entity = getEntityFromResource(this.companyData);
        if (entity instanceof Collection) {
        	repository.save((Collection<Company>)entity);
        } else {
            repository.save((Company) entity);
        }
    }

    @SuppressWarnings("unchecked")
    public void populate(ComputerRepo repository) {
        Object entity = getEntityFromResource(this.computerData);
        if (entity instanceof Collection) {
        	repository.save((Collection<Computer>)entity);
        } else {
            repository.save((Computer) entity);
        }
    }    
    
    private Object getEntityFromResource(Resource resource) {
        try {
            return this.resourceReader.readFrom(resource, this.getClass().getClassLoader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
