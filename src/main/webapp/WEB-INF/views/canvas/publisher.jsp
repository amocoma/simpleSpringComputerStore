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
 		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/js/canvas/json2.js"></script>
        <script type="text/javascript" src="/js/canvas/sdk/canvas-all.js"></script>
        <script type="text/javascript" src="/js/canvas/publisher/publisher.js"></script>
        <style type="text/css">
        	.selectedCanvas {
        		background-color: #8ab529;
        	}

        </style>
        <script>
        	var sr;
	        $(document).ready(function() {
	        	if (self === top) { alert("This canvas app must be included within an iframe"); }
	
	            Sfdc.canvas(function() {
	                sr = JSON.parse('${canvasRequestJson}');
	                myPublisher.init(sr, ${ua.device.mobile});
	                var handlers = myPublisher.handlers();
	                Sfdc.canvas.client.subscribe(sr.client, handlers.subscriptions);
	                myPublisher.updateContent();
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
	        	
	        	$(".selectData").dblclick(function() {
	        		var hadClass = $(this).hasClass("selectedCanvas")
	        		$(".selectedCanvas").each(function( index ) {
	        			$(this).removeClass("selectedCanvas");
	        		});
	        		if(hadClass){
	        			myPublisher.contentDeleted();
	        		}else{
	        			$(this).addClass("selectedCanvas");
	        			myPublisher.contentSet({'objPath':this.dataset.ref,"title":this.dataset.ref_title});
	        		}
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
			<c:if test="${!ua.device.mobile}" >
		    	<div class="row">
		    		<div class="col-xs-1"><b>Name:</b></div>
		    		<div id="name" class="col-xs-2"></div>
		    		<div class="col-xs-2"><b>Location:</b></div>
		    		<div id="location" class="col-xs-2"></div>		    		
		    		<div class="col-xs-1"><b>Header:</b></div>
		    		<div id="header-enabled" class="col-xs-1"></div>
		    		<div class="col-xs-1"><b>Share:</b></div>
		    		<div id="share-enabled" class="col-xs-1"></div>		    		
		    	</div>
		    </c:if>
		    <%--<div id="publisher" style="height:${canvasRequest.context.environmentContext.dimensions.clientHeight}">--%>
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
	    	
	    	<form role="form">
			  <div class="form-group">
			    <label for="auxText">Chatter Text</label>
			    <input type="text" class="form-control" id="auxText" placeholder="Some Text" style="font-size: inherit;" value="Chatter Text">
			  </div>
			  <div class="form-group">
			    <label for="description">Description</label>
			    <textarea class="form-control" rows="3" id="description" onmouseup='myPublisher.resize()' style="font-size: inherit;" value="Some Description"></textarea>
			  </div>
			  <div class="form-group">
			    <label for="parameters">Parameters</label>
			    <textarea class="form-control" rows="1" id="parameters" onmouseup='myPublisher.resize()' style="font-size: inherit;">{}</textarea>
			  </div>
			</form>
		</div>
	</body>
</html>

