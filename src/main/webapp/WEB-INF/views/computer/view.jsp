<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link rel="stylesheet" type="text/css" media="screen" href="/css/bootstrap.min.css"> 
        <link rel="stylesheet" type="text/css" media="screen" href="/css/main.css"> 
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
		          <input type="text" class="form-control" placeholder="Search">
		        </div>
		        <button type="submit" class="btn btn-default">SEARCH</button>
		        <a class="btn btn-primary" id="add" href="/computers/add">Add a new computer</a>		        
		      </form>
		    </div><!-- /.navbar-collapse -->
		 </div>
		</div>
		<div class="container">
			<h1>${computer.name}</h1>
			<form class="form-horizontal" role="form">
				<div class="form-group">
				  <label  class="col-sm-2 control-label">Name</label>
				  <div class="col-sm-10">
				   	  <p class="form-control-static">${computer.name}</p>
				  </div>
				</div>
				<div class="form-group">
				  <label for="discontinued" class="col-sm-2 control-label">Discontinued</label>
				  <div class="col-sm-10">
				   	  <p class="form-control-static">${computer.discontinued}</p>
				  </div>
				</div>
				<div class="form-group">
				  <label for="introduced" class="col-sm-2 control-label">Introduced</label>
				  <div class="col-sm-10">
				   	  <p class="form-control-static">${computer.introduced}</p>
				  </div>
				</div>
				<div class="form-group">
				  <label for="company" class="col-sm-2 control-label">Company</label>
				  <div class="col-sm-10">
				   	  <p class="form-control-static">${computer.company.name}</p>
				  </div>
				</div>
			</form>
	        <div class="actions pull-right">
	            <a href="/computers/edit/${computer.id}" class="btn btn-danger">Edit</a> or 
	            <a href="/computers/" class="btn btn-primary">Cancel</a> 
	        </div>
		</div>
	</body>
</html>