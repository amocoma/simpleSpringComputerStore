package com.salesforce.de.dg.heroku.model.canvas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CanvasConsumer {

	public String key;
	public String secret;
	
	public CanvasConsumer(){}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("secret")
	public String getSecret() {
		return secret;
	}

	@JsonProperty("secret")
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
