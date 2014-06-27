package com.salesforce.de.dg.heroku.util;

import java.util.Arrays;

public class ApplicationContext {
    private String[] profiles;
    private String profileStr;
    private String[] services;
    private String serviceStr;
    private String host;
    private String port;
    private String herokuConnect;

    public ApplicationContext(String[] profiles, String[] services) {
    	this(profiles, services, "", "");
    }

    public ApplicationContext(String[] profiles, String[] services, String host, String port) {
    	this(profiles, services, host, port, false);
    }
    
    public ApplicationContext(String[] profiles, String[] services, String host, String port, boolean herokuConnect) {
        this.profiles = profiles;
        this.profileStr = Arrays.toString(profiles);
        this.services = services;
        this.serviceStr = Arrays.toString(services);
        this.host = host;
        this.port = port;
        this.herokuConnect = herokuConnect?"block":"none";
    }
    
    public String[] getProfiles() {
        return profiles;
    }

    public void setProfiles(String[] profiles) {
        this.profiles = profiles;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProfileStr() {
		return profileStr;
	}

	public void setProfileStr(String profileStr) {
		this.profileStr = profileStr;
	}

	public String getServiceStr() {
		return serviceStr;
	}

	public void setServiceStr(String serviceStr) {
		this.serviceStr = serviceStr;
	}

	public String getHerokuConnect() {
		return herokuConnect;
	}

	public void setHerokuConnect(String herokuConnect) {
		this.herokuConnect = herokuConnect;
	}
    
}
