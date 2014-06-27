<%@ page import="com.salesforce.de.dg.heroku.util.canvas.CanvasSignedRequest" %>
<%@ page import="java.util.Map" %>
<%
    if (request.getAttribute("canvasRequest") == null) {%>
        This App must be invoked via a signed request!<%
        return;
    }
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <title>Force.com Canvas</title>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/canvas/mobilenav/mobile-nav.css" />
        <script type="text/javascript" src="/js/canvas/sdk/canvas-all.js"></script>
        <script type="text/javascript" src="/js/canvas/json2.js"></script>
        <script type="text/javascript" src="/js/canvas/chatter-talk.js"></script>

        <script>
            if (self === top) {
                // Not in Iframe
                alert("This canvas app must be included within an iframe");
            }

            Sfdc.canvas(function() {
                var sr = JSON.parse('${canvasRequestJson}');
                var photoUri = sr.context.user.profileThumbnailUrl +  "?oauth_token=" + sr.client.oauthToken;
                Sfdc.canvas.byId('header').style.backgroundImage =  "url('"+(photoUri.indexOf("http")==0 ? "" :sr.client.instanceUrl) + photoUri+"')";
            });

        </script>


	</head>
	<body>
		<div id="page" class="container" style="height:${canvasRequest.context.environmentContext.dimensions.height}">
	        <div id="content">
	            <div id="header">
	                <h1>Welcome to Canvas in the Mobile Navigation!</h1>
	            </div>
	            <div id="canvas-content">
	            <h2>With Force.com Canvas in Mobile, you can:</h2>
	            <ul>
	              <li>Expose your application in Salesforce1, on any client.</li>
	              <li>Write your application in any language, using "location aware" context information.</li>
	              <li>Leverage the Force.com Canvas SDK to react to live client-side events, including the Streaming API.</li>
	            </ul>
	            </div>
	        </div>
	
	        <div id="footercont">
	            <div id="footerleft">
	                <p>Powered By: <a title="Heroku" href="#" onclick="window.top.location.href='http://www.heroku.com'"><strong>Heroku</strong></a></p>
	            </div>
	            <div id="footerright">
	                <p>Salesforce: <a title="Safe Harbor" href="http://www.salesforce.com/company/investor/safe_harbor.jsp"><strong>SafeHarbor</strong></a></p>
	            </div>
	        </div>
		</div>
	</body>
</html>