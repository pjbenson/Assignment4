<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Profile</title>

<!-- Latest compiled and minified CSS -->
<style type="text/css">
footer {
	margin-top: 20px;
	padding-top: 20px;
	padding-bottom: 20px;
	background-color: #efefef;
}

.container {
	width: 0 auto;
	margin: 0 auto;
	margin-left: 250px;
}

/* count indicator near icons */
.nav>li .count {
	position: absolute;
	bottom: 12px;
	right: 6px;
	font-size: 9px;
	background: rgba(51, 200, 51, 0.55);
	color: rgba(255, 255, 255, 0.9);
	line-height: 1em;
	padding: 2px 4px;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	-ms-border-radius: 10px;
	-o-border-radius: 10px;
	border-radius: 10px;
}

/* indent 2nd level */
.list-unstyled li>ul>li {
	margin-left: 10px;
	padding: 8px;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
</head>

<body>

	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Edit</h4>
				</div>
				<div class="modal-body">
					<div class="col-xs-5"></div>
				</div>
				<div class="form-group"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>


	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">Assignment 4</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a
							href="${pageContext.request.contextPath}/adminProfile.html">${sessionScope.user.userName}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout.html">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->

		<div class="jumbotron">
			<h2>Add Stock</h2>
			<form:form method="POST" commandName="stock" class="form-signin">
				<form:input path="title" class="form-control" placeholder="Title" />
				<form:input path="manufacturer" class="form-control"
					placeholder="Manufacturer" />
				<form:select path="category" items="${categories}"
					placeholder="Category" class="form-control" />
				<form:input path="price" class="form-control" placeholder="Price" />
				<form:input path="quantity" class="form-control"
					placeholder="Quantity" />
				<div class="form-group">
					<input type="submit" value="Submit"
						class="btn btn-large btn-success" />
				</div>
			</form:form>
		</div>
	</div>
	<!-- /container -->

</body>
</html>