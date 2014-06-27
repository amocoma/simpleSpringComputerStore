/**
 * Copyright (c) 2011-2013, salesforce.com, inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 *
 *    Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *    following disclaimer.
 *
 *    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *    the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 *    Neither the name of salesforce.com, inc. nor the names of its contributors may be used to endorse or
 *    promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.de.dg.heroku.model.canvas;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes meta-data about the canvas connected app
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CanvasApplicationContext {

    private String namespace;
    private String developerName;
    private String name;
    private String canvasUrl;
    private String applicationId;
    private String version;
    private String authType;
    private String referenceId;
    private List<String> options;

    /**
     * Developer org's unique namespace. Can be null.
     * @return namespace
     */
    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    @JsonProperty("namespace")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Developer name of the app. Note this is not the same as the application name.
     * @return developerName
     */
    @JsonProperty("developerName")
    public String getDevName() {
        return developerName;
    }

    @JsonProperty("developerName")
    public void setDevName(String devName) {
        this.developerName = devName;
    }

    /**
     * Name of the application.  
     * @return name
     */
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fully qualified canvas URL. Example: https://somedomain.com/canvas.html
     * @return canvasUrl
     */
    @JsonProperty("canvasUrl")
    public String getCanvasUrl() {
        return canvasUrl;
    }

    @JsonProperty("canvasUrl")
    public void setCanvasUrl(String canvasUrl) {
        this.canvasUrl = canvasUrl;
    }

    /**
     * The connected app's application Id.
     * @return applicationId
     */
    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * The version of the connected app
     * @return version information example: "1.0"
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * The authType of connected app. Valid values "signed_request" or "oauth".
     * @return authType
     */
    @JsonProperty("authType")
    public String getAuthType() {
        return authType;
    }

    @JsonProperty("authType")
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * The customer facing identifier for this canvas application.
     */
    @JsonProperty("referenceId")
    public String getReferenceId() {
        return this.referenceId;
    }

    @JsonProperty("referenceId")
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Returns a list of options that have been selected. Example: HideHeader, HideShare
     * these are used to suppress the header and footer in the publisher.
     */
    @JsonProperty("options")
    public List<String> getOptions() {
        if (null == this.options){
            this.options = new ArrayList<String>(0);
        }
        return this.options;
    }

    @JsonProperty("options")
    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString()
    {
        return namespace+ ","+
               developerName+ ","+
               applicationId+ ","+
               version+ ","+
               authType+ ","+
               referenceId + "," +
               canvasUrl;
    }
}
