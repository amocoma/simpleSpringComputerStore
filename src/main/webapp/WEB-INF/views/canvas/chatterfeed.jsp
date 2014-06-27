<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <title>Force.com Canvas</title>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" href="/css/canvas/chatterfeed/chatterfeed${ua.device.mobile ? '-mobile':''}.css"/>
 		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/js/canvas/sdk/canvas-all.js"></script>
        <script type="text/javascript" src="/js/canvas/json2.js"></script>
        <script type="text/javascript" src="/js/canvas/chatter-talk.js"></script>
        <script>
        $(document).ready(function() {
        	if (self === top) { alert("This canvas app must be included within an iframe"); }
            Sfdc.canvas(function() {
                var sr = JSON.parse('${canvasRequestJson}');
                var photoUri = sr.context.user.profileThumbnailUrl +  "?oauth_token=" + sr.client.oauthToken;
                Sfdc.canvas.byId('profile-pic').src =  photoUri.indexOf("http")==0 ? photoUri :sr.client.instanceUrl + photoUri;
                Sfdc.canvas.byId('profile-pic').alt = sr.context.user.firstName + " " + sr.context.user.lastName;
                Sfdc.canvas.byId('parameters').innerHTML = JSON.stringify(sr.context.environment.parameters);
                var objPath = sr.context.environment.parameters.objPath;
                if(objPath!=null){
                    retrieveObjDat(objPath);                	
                }

               	Sfdc.canvas.client.autogrow(sr.client);
            });
        });           
        function retrieveObjDat(objPath){
        	$.ajax({
                url: objPath
            }).then(function(data) {
               updateEntity(data);
            });
        }
            
        function updateEntity(data){
            Sfdc.canvas.byId('objHeader').innerHTML = data.name;
            Sfdc.canvas.byId('objImg').src = "http://lorempixel.com/400/200/technics/" + encodeURIComponent(data.name);
            if(data.introduced != null){
                Sfdc.canvas.byId('introduced').innerHTML = data.introduced;            
                Sfdc.canvas.byId('introduced').parentNode.style.display = "block";
            }
            if(data.introduced != null){
                Sfdc.canvas.byId('discontinued').innerHTML = data.introduced;            
                Sfdc.canvas.byId('discontinued').parentNode.style.display = "block";
            }
            Sfdc.canvas.byId('obj').style.display = 'block';
           	Sfdc.canvas.client.autogrow(sr.client);
        }
            
        </script>
	</head>
	<body>
		<div class="container">
         <img id="profile-pic" src="" alt="">
         <span class="right-just"><h3>Canvas - Heroku in the Chatter Feed!</h3></span>
         <ul>
             <li>Parameter passed from force.com: <span id='parameters'></span></li>
             <li>Your 'Live' content:</li>
         </ul>
		<div id="obj" style="display:none;">
			<h3 id="objHeader"></h3>
			<div class="row" style="display:none;">
				<div class="col-xs-2">Introduced:</div>
				<div class="col-xs-3" id="introduced"></div>
			</div>
			<div class="row" style="display:none;">
				<div class="col-xs-2">Discontinued:</div>
				<div class="col-xs-3" id="discontinued"></div>
			</div>
			<div class="row"  style="display:none;">
				<div class="col-xs-2">Company:</div>
				<div class="col-xs-3" id="company"></div>
			</div>
			<div class="row">
				<div class="col-xs-5">
					<img id="objImg" src=""/>						
				</div>
			</div>
		</div>
	    </div>
	</body>
</html>