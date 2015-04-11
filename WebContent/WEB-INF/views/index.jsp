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

<title>Assignment4</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="navbar.css" rel="stylesheet" type="text/css">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="bootstrap/docs/assets/js/ie-emulation-modes-warning.js"
	type="text/javascript"></script>
</head>

<body>

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
					<a class="navbar-brand" href="#">Assignment 4</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="loginform.html">Login</a></li>
						<li><a href="register.html">Register</a></li>
					</ul>
					<c:if test="${not empty sessionScope.user.userName}">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle"
								role="button" data-toggle="dropdown"><i
									class="glyphicon glyphicon-user"></i>
									${sessionScope.user.userName} <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="profile.html">My Profile</a></li>
								</ul></li>
							<li><a href="${pageContext.request.contextPath}/logout.html"><i
									class="glyphicon glyphicon-lock"></i>Logout</a></li>
						</ul>
					</c:if>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Assignment4</h1>
			<h2>Login credentials:</h2>
			<table class="table table-bordered ">
				<thead>
					<tr>
						<th>Username</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>admin</td>
						<td>admin</td>
					</tr>
					<tr>
						<td>user</td>
						<td>user</td>
					</tr>
				</tbody>
			</table>
			<p>
				<a class="btn btn-lg btn-primary" href="loginform.html"
					role="button">Login &raquo;</a>
			</p>
		</div>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
