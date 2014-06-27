package com.salesforce.de.dg.heroku.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.service.common.CanvasConsumerInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesforce.de.dg.heroku.model.canvas.CanvasRequest;
import com.salesforce.de.dg.heroku.repository.ComputerRepo;
import com.salesforce.de.dg.heroku.util.canvas.CanvasSignedRequest;
import com.salesforce.de.dg.heroku.util.canvas.UserAgent;

@Controller
@Profile(value="canvasconsumer")
@RequestMapping(value = { "/canvas" })
public class Canvas extends AbstractWebController{

	// heroku config:add CANVAS_CONSUMER='{"key":"3MVG9WtWSKUDG.x7e3akS4D0FCgftevg98A0.4XRahIvQxhK_r6J5mxZKJ3nBfHC1zAw0nf6YKciALB02PRn1","secret":"7058261916322322946"}'
	
	@Autowired
	ComputerRepo computerRepo;
	@Autowired
	private Environment env;
	@Autowired(required = false)
	private CanvasConsumerInfo cci;
	
	private static final String SIGNED_REQUEST_PARAM = "signed_request";

	private static final String NO_SIGNED_REQUEST_RESOURCE = "canvas/noSignedRequest";
	private static final String NO_CONSUMER_SERCET_DEFINED = "canvas/noConsumerSecretDefined";
	
	@RequestMapping(method = RequestMethod.POST)
	public String display(@RequestParam(value=SIGNED_REQUEST_PARAM, required=true) String srString, @RequestHeader(value="User-Agent") String userAgent, ModelMap model) {
		request.setAttribute("ua", UserAgent.parse(userAgent));
		if(srString == null || srString.trim().equals("")){
			return NO_SIGNED_REQUEST_RESOURCE;
		}else if(cci == null){
			return NO_CONSUMER_SERCET_DEFINED;
		}
		CanvasRequest cr = CanvasSignedRequest.verifyAndDecode(srString, cci.getCanvasConsumer().getSecret());
		request.setAttribute("canvasRequest", cr);
		request.setAttribute("canvasRequestJson", CanvasSignedRequest.toString(cr));
		System.out.println(CanvasSignedRequest.toString(cr));
		String displayType = cr.getContext().getEnvironmentContext().getDisplayLocation();
		displayType = displayType==null||displayType.trim().equals("")?"default":displayType.toLowerCase();
		return "forward:/canvas/" + displayType;
	}	

	@RequestMapping(method = RequestMethod.GET)
	public String display() {
		return NO_SIGNED_REQUEST_RESOURCE;
	}

	@RequestMapping(value="/publisher", method = RequestMethod.POST)
	public String displayInPublisher(){
		return "canvas/publisher";
	}

	@RequestMapping(value="/chatter", method = RequestMethod.POST)
	public String displayInChatter(){
		return "canvas/chatter";
	}
	
	@RequestMapping(value="/chatterfeed", method = RequestMethod.POST)
	public String displayInChatterFeed(ModelMap model){
		return "canvas/chatterfeed";
	}
	
	@RequestMapping(value="/mobilenav", method = RequestMethod.POST)
	public String displayInMobileNav(){
		return "canvas/mobilenav";
	}

	@RequestMapping(value="/*", method = RequestMethod.POST)
	public String displayInDefault(){
		return "canvas/default";
	}	
	
}
