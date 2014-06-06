<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css"> 
        <script src="/js/jquery-1.11.1.min.js"></script>
        
		<script>
		   var sort = "${sort}";
		   var query = "${query}";
		   $(function(){
		        $('#query').on('click', function(e){
		            e.preventDefault();
		            query = $('#queryText')[0].value;
					self.location="/computers/?query="+query+"&sort="+sort;
		        })
		    })
		</script>

	</head>
	<body>
       <div class="navbar navbar-inverse" role="navigation">
       	<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			    <span class="sr-only">Toggle navigation</span>
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			  </button>
			  <a class="navbar-brand" href="/">Computers database</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		      <form class="navbar-form navbar-right" role="search">
		        <div class="form-group">
		          <input type="text" id="queryText" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" id="query" class="btn btn-default">SEARCH</button>
		        <a class="btn btn-primary" id="add" href="/computers/add">Add a new computer</a>		        
		      </form>
		    </div><!-- /.navbar-collapse -->
		 </div>
		</div>
		<div class="container">
			<c:url var="firstUrl" value="/computers/1" />
			<c:url var="lastUrl" value="/computers/${computerPage.totalPages}" />
			<c:url var="prevUrl" value="/computers/${currentIndex - 1}" />
			<c:url var="nextUrl" value="/computers/${currentIndex + 1}" />
			<h1>${found} Computers found</h1>
			<div style="text-align: center;">
			    <ul class="pagination"  style="float:none;display: inline-block;">
			        <c:choose>
			            <c:when test="${currentIndex == 1}">
			                <li class="disabled"><a href="#">&lt;&lt;</a></li>
			                <li class="disabled"><a href="#">&lt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="${firstUrl}">&lt;&lt;</a></li>
			                <li><a href="${prevUrl}">&lt;</a></li>
			            </c:otherwise>
			        </c:choose>
			        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
			            <c:url var="pageUrl" value="/computers/${i}" />
			            <c:choose>
			                <c:when test="${i == currentIndex}">
			                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:when>
			                <c:otherwise>
			                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			        <c:choose>
			            <c:when test="${currentIndex == computerPage.totalPages}">
			                <li class="disabled"><a href="#">&gt;</a></li>
			                <li class="disabled"><a href="#">&gt;&gt;</a></li>
			            </c:when>
			            <c:otherwise>
			                <li><a href="${nextUrl}">&gt;</a></li>
			                <li><a href="${lastUrl}">&gt;&gt;</a></li>
			            </c:otherwise>
			        </c:choose>
			    </ul>
			</div>
			<table class="table table-striped">
			    <tr>
			        <th>Computer name</th>
			        <th>Introduced</th>
			        <th>Discontinued</th>
			        <th>Company</th>
			    </tr>
			    <c:forEach items="${computers}" var="computer">
				   <tr>
					   <td><a href="/computers/view/${computer.id}">${computer.name}</a></td>
				       <td>${computer.introduced}</td>
				       <td>${computer.discontinued}</td>
				       <td>${computer.company.name}</td>
				    </tr>
				</c:forEach>
			</table>		
		</div>
	</body>
</html>



