<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <title>Force.com Canvas</title>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/canvas/publisher/publisher${ua.device.mobile ? '-mobile':'-desktop'}.css"/>
    	<link rel="stylesheet" type="text/css" href="/css/canvas/default/tabs.css" />
 		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/js/canvas/json2.js"></script>
        <script type="text/javascript" src="/js/canvas/sdk/canvas-all.js"></script>
        <script type="text/javascript" src="/js/canvas/default/tabs.js"></script>        
        
        <style type="text/css">
        	.selectedCanvas {
        		background-color: #8ab529;
        	}

        </style>
        <script>
        	var sr;
	        $(document).ready(function() {
	        	if (self === top) { alert("This canvas app must be included within an iframe"); }
                sr = JSON.parse('${canvasRequestJson}');	
	            Sfdc.canvas(function() {
		            var photoUri = sr.context.user.profileThumbnailUrl +  "?oauth_token=" + sr.client.oauthToken;
		            //Sfdc.canvas.client.subscribe(sr.client, handlers.subscriptions);
		            Sfdc.canvas.byId('header').style.backgroundImage =  "url('"+(photoUri.indexOf("http")==0 ? "" :sr.client.instanceUrl) + photoUri+"')";
		            //resetSize();
		            initTabs();
	                //myPublisher.updateContent();
	                retrieveData();
	            });

	        
	        });
	        var size = 15;
	        function retrieveData(){
	        	$.ajax({
	                url: "/api/computers?page=1&size="+size
	            }).then(function(data) {
	               updateList(data);
	            });
	        }
	        function retrieveData(page){
	        	$.ajax({
	                url: "/api/computers?page=" + page +"&size=" + size
	            }).then(function(data) {
	               updateList(data);
	            });
	        }
	        function updateList(dataList){
	        	var rh = $("#recordsHeader");
	        	rh.empty();
	        	rh.append("<tr>" + "<th>Name</th>" + "<th>Introduced</th>" + "<th>Discontinued</th>" + "<th>Company</th>" + "</tr>");
	        	var rb = $("#recordsBody");
	        	rb.empty();
	        	$.each(dataList._embedded.computers, function(i, item) {
	        		rb.append("<tr class='selectData' data-ref='" + getLocation(item._links.self.href).pathname + "' data-ref_title='" + item.name + "'>" + "<td>"+item.name+"</td>" + "<td>"+(item.introduced==null?'':item.introduced)+"</td>" + "<td>"+(item.discontinued==null?'':item.discontinued)+"</td>" + "<td>" + (item.company==null?'':item.company.name) + "</td>" + "</tr>");
	        	});
	        	
	        	// create pagination
				createPagination(dataList.page, "pagination");
				$("#navTop").empty();
				$("#pagination").clone().prop({ id: "paginationTop"}).appendTo("#navTop");
	        	
      	
	        	Sfdc.canvas.client.autogrow(sr.client);
	        }
	        
	        var getLocation = function(href) {
	            var l = document.createElement("a");
	            l.href = href;
	            return l;
	        };
	        
	        function createPagination(page, id){
	        	var pageStat = page;
	        	var pag = $("#" + id);
	        	pag.empty();
	        	
	        	var startAt = ((pageStat.number>3) && (pageStat.totalPages > 10)) ? (pageStat.number-3):pageStat.number;
	        	var endAt = startAt + 10;
	        	
	        	if(endAt > pageStat.totalPages - 1){
	        		endAt = pageStat.totalPages;
	        	}
	        	if(startAt == 1){
	        		pag.append("<li class='disabled'><a>&lt;&lt;</a></li>");
	        		pag.append("<li class='disabled'><a>&lt;</a></li>");
	        	}else{
	        		pag.append("<li><a onClick='retrieveData(0)'>&lt;&lt;</a></li>");
	        		pag.append("<li><a onClick='retrieveData(" + (startAt - 1) + ")'>&lt;</a></li>");	        		
	        	}
	        	for (p = startAt; p < endAt; p++) { 
					var pageClick = "retrieveData(" + (p) + ")";
					if(pageStat.number == p){
						pag.append("<li class='active'><a onClick='" + pageClick + "'>" + (p+1) +"</a></li>");
					}else{
						pag.append("<li><a onClick='" + pageClick + "'>" + (p+1) +"</a></li>");
					}
	        	}
	        	if(pageStat.number == (pageStat.totalPages-1)){
	        		pag.append("<li class='disabled'><a>&gt;</a></li>");
	        		pag.append("<li class='disabled'><a>&gt;&gt;</a></li>");
	        	}else{
	        		pag.append("<li><a onClick='retrieveData(" + (pageStat.number) + ")'>&gt;</a></li>");
	        		pag.append("<li><a onClick='retrieveData(" + (pageStat.totalPages) + ")'>&gt;&gt;</a></li>");	        		
	        	} 	
	        }
        </script>
	</head>
	<body>
		<div class="container" style="text-align: center;">
			<div id="default">
		        <div id="header">
		            <h1>Hello <span id='fullname'>${canvasRequest.context.userContext.fullName}</span>!</h1>
		            <h2>Welcome to the Force.com Canvas!</h2>
		        </div>
		
		        <div class="tab-box">
		            <a href="javascript:;" class="tabLink activeLink" id="context">Context</a>
		            <a href="javascript:;" class="tabLink " id="computerStore">Computer Store</a>
		            <a href="javascript:;" class="tabLink " id="resize">Resize</a>
		            <a href="javascript:;" class="tabLink " id="events">Events</a>
		            <a href="javascript:;" class="tabLink " id="scroll">Scrolling</a>
		        </div>
		
		        <div class="tabcontent paddingAll" id="context-1">
		            <jsp:include page="default/context.jsp"/>
		        </div>
		
		        <div class="tabcontent paddingAll hide" id="computerStore-1">		
		 			<%--<div id="publisher" style="height:${canvasRequest.context.environmentContext.dimensions.clientHeight}">--%>
					<h1>Canvas in Chatter</h1>
					<div id="navTop">
			    	</div>
				    <div id="records" class="table-responsive table-condensed">
						<table class="table">
							<thead id="recordsHeader">
		
							</thead>
							<tbody id="recordsBody">
							</tbody>
						</table>
		
				    </div>
					<div style="text-align: center;" id="navBottom">
				    	<ul id="pagination" class="pagination"  style="float:none;display: inline-block;">
				    	</ul>
			    	</div>		
				</div>
		        <div class="tabcontent paddingAll hide" id="resize-1">
		            <jsp:include page="default/resize.jsp"/>
		        </div>
		
		        <div class="tabcontent paddingAll hide" id="events-1">
		            <jsp:include page="default/events.jsp"/>
		        </div>
		        <div class="tabcontent paddingAll hide" id="scroll-1">
		            <jsp:include page="default/scroll.jsp"/>
		        </div>
		        <div id="footercont">
		            <div id="footerleft">
		                <p>Powered By: <a title="Heroku" href="#" onclick="window.top.location.href='http://www.heroku.com'"><strong>Heroku</strong></a>
		                </p>
		            </div>
		            <div id="footerright">
		                <p>Salesforce: <a title="Safe Harbor" href="http://www.salesforce.com/company/investor/safe_harbor.jsp"><strong>SafeHarbor</strong></a>
		                </p>
		            </div>
		        </div>			
			</div>
		</div>
	</body>
</html>

