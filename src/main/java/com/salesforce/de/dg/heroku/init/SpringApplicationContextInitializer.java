package com.salesforce.de.dg.heroku.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.MongoServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;
import org.springframework.cloud.service.common.PostgresqlServiceInfo;
import org.springframework.cloud.service.common.TreasureDataInfo;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringApplicationContextInitializer implements ApplicationContextInitializer<AnnotationConfigWebApplicationContext> {
   
    private static final Log logger = LogFactory.getLog(SpringApplicationContextInitializer.class);

    private static final Map<Class<? extends ServiceInfo>, String> serviceTypeToProfileName =
            new HashMap<Class<? extends ServiceInfo>, String>();
    private static final List<String> validAdditionalProfiles = Arrays.asList("treasuredata");
    private static final List<String> validPersistenceProfiles = Arrays.asList("postgres", "mongodb", "mysql");

    public static final String IN_MEMORY_PROFILE = "in-memory";

    static {
        serviceTypeToProfileName.put(MongoServiceInfo.class, "mongodb");
        serviceTypeToProfileName.put(PostgresqlServiceInfo.class, "postgres");
        serviceTypeToProfileName.put(MysqlServiceInfo.class, "mysql");
        serviceTypeToProfileName.put(TreasureDataInfo.class, "treasuredata");
    }

    @Override
    public void initialize(AnnotationConfigWebApplicationContext applicationContext) {
        Cloud cloud = getCloud();

        ConfigurableEnvironment appEnvironment = applicationContext.getEnvironment();
        
        ArrayList<String> profiles = new ArrayList<String>();
        profiles.addAll(getCloudProfile(cloud));
        if (profiles.size() == 0) {
        	profiles.addAll(getActiveProfile(appEnvironment));
        }

        //check for persistence layer
        int persistenceProfiles = 0;
        for(String profile : profiles){
        	persistenceProfiles += validPersistenceProfiles.indexOf(profile)!=-1?1:0;
        }
        if(persistenceProfiles == 0){
        	profiles.add(IN_MEMORY_PROFILE);
        }
        
        for (String profile : profiles) {
            appEnvironment.addActiveProfile(profile);
        }
    }

    public ArrayList<String> getCloudProfile(Cloud cloud) {
        ArrayList<String> profileList = new ArrayList<String>();
    	List<String> profiles = new ArrayList<String>();
    	if (cloud == null) {
            return profileList;
        }

        List<ServiceInfo> serviceInfos = cloud.getServiceInfos();

        logger.info("Found serviceInfos: " + StringUtils.collectionToCommaDelimitedString(serviceInfos));
        int persistenceProfiles = 0;
        for (ServiceInfo serviceInfo : serviceInfos) {
        	System.out.println(">>>>>>>>>>>>>>>>>> " + serviceInfo.getClass().getName());
        	if (serviceTypeToProfileName.containsKey(serviceInfo.getClass())) {
            	persistenceProfiles += validPersistenceProfiles.indexOf(serviceTypeToProfileName.get(serviceInfo.getClass()))!=-1?1:0;
                profiles.add(serviceTypeToProfileName.get(serviceInfo.getClass()));
            }
        }

        if (persistenceProfiles > 1) {
            throw new IllegalStateException(
                    "Only one service of the following types may be bound to this application: " +
                            serviceTypeToProfileName.values().toString() + ". " +
                            "These services are bound to the application: [" +
                            StringUtils.collectionToCommaDelimitedString(profiles) + "]");
        }

        for(String profile : profiles){
        	profileList.addAll(createProfileNames(profile, "cloud"));
        }

        return profileList;
    }

    private Cloud getCloud() {
        try {
            CloudFactory cloudFactory = new CloudFactory();
            return cloudFactory.getCloud();
        } catch (CloudException ce) {
            return null;
        }
    }

    private ArrayList<String> getActiveProfile(ConfigurableEnvironment appEnvironment) {
        List<String> serviceProfiles = new ArrayList<String>();

        for (String profile : appEnvironment.getActiveProfiles()) {
            if (validPersistenceProfiles.contains(profile)) {
                serviceProfiles.add(profile);
            }
        }

        if (serviceProfiles.size() > 1) {
            throw new IllegalStateException("Only one active Spring profile may be set among the following: " +
            		validPersistenceProfiles.toString() + ". " +
                    "These profiles are active: [" +
                    StringUtils.collectionToCommaDelimitedString(serviceProfiles) + "]");
        }
 
        for (String profile : appEnvironment.getActiveProfiles()) {
            if (validAdditionalProfiles.contains(profile)) {
                serviceProfiles.add(profile);
            }
        }        

        ArrayList<String> profiles = new ArrayList<String>();
        for(String profile : serviceProfiles){
        	profiles.addAll(createProfileNames(profile, "local"));
        }

        return profiles;
    }

    private ArrayList<String> createProfileNames(String baseName, String suffix) {
    	String[] profiles = new String[2];
    	profiles[0] = baseName;
    	profiles[1] = baseName + "-" + suffix;
        logger.info("Setting profile names: " + StringUtils.arrayToCommaDelimitedString(profiles));
        return new ArrayList<String>(Arrays.asList(profiles));
    }
}