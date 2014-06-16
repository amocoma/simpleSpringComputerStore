package com.salesforce.de.dg.heroku.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;

import com.salesforce.de.dg.heroku.service.TreasureDataService;
import com.salesforce.de.dg.heroku.util.ApplicationContext;

public abstract class AbstractWebController {
	@Autowired(required = false)
    private Cloud cloud;	
	@Autowired
    private Environment springEnvironment;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	protected TreasureDataService tdService;
	
    public ApplicationContext appContext() {
    	String inetInfo[] = getInetInfo();
        return new ApplicationContext(springEnvironment.getActiveProfiles(), getServiceNames(), inetInfo[0], inetInfo[1]);
    }
	
    private String[] getInetInfo(){
    	
    	return new String[]{request.getLocalAddr(), String.valueOf(request.getLocalPort())};
    }
    
	private String[] getServiceNames() {
		if (cloud != null) {
		    final List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
		
		    List<String> names = new ArrayList<String>();
		    for (ServiceInfo serviceInfo : serviceInfos) {
		        names.add(serviceInfo.getId());
		    }
		    return names.toArray(new String[names.size()]);
		} else {
		    return new String[]{};
		}
	}
	
	
}
