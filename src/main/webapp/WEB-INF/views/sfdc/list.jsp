<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css"> 
        <script src="/js/jquery-1.11.1.min.js"></script>
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
			</div>
			  <ul class="nav navbar-nav navbar-right">
        		<li><a href="/computers">Browse Computers</a></li>
			  </ul>
			<!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    </div><!-- /.navbar-collapse -->
		 </div>
		</div>
		<div class="container">
			<form:form modelAttribute="af"
			           name="myForm" 
			           action="/sfdc/edit"
			           method="post" class="form-horizontal" role="form">

				<table class="table table-striped">
				    <tr>
				        <th>Id</th>
				        <th>sfdcId</th>
				        <th>Name</th>
				    </tr>
				    <c:forEach items="${af.accounts}" var="account" varStatus="status">
					   <tr>
						   <td>${account.id}<input id="accounts[${status.index}].id" name="accounts[${status.index}].id" type="hidden" value="${account.id}" /></td>
					       <td>${account.sfid}<input id="accounts[${status.index}].sfid" name="accounts[${status.index}].sfid" type="hidden" value="${account.sfid}" /></td>
					       <td><input id="accounts[${status.index}].name" name="accounts[${status.index}].name" placeholder="Name" class="form-control" value="${account.name}"/></td>
					    </tr>
					</c:forEach>
				</table>		
		        <div class="actions pull-right">
		            <input type="submit" value="Save" class="btn btn-primary">
		        </div>
			</form:form>
		</div>
	</body>
</html>



