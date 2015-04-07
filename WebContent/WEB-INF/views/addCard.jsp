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

<title>AddAccount</title>
<script type="text/javascript"
	src="bootstrap/dist/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<link href="bootstrap/datepicker/css/datepicker.css" rel="stylesheet"
	type="text/css">

<!-- Bootstrap core CSS -->
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

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
					<a class="navbar-brand" href="index.html">Assignment 4</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="">${sessionScope.user.password}</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Add Credit Card Details</h1>
			<form:form action="/Assignment4/addCard.html" method="post" commandName="creditCard"
				class="form-horizontal">
				<table border="0">
					<tr>
						<td>Card Number:</td>
						<td><form:input path="cardNumber" /></td>

					</tr>

					<tr>
						<td>Expiry Month:</td>
						<td><form:password path="expiryMonth" /></td>
					</tr>
					<tr>
						<td>Expiry Year:</td>
						<td><form:input path="expiryYear" /></td>
					</tr>
					<tr>
						<td>Card Type:</td>
						<td><form:select path="cardType" items="${cardTypes}" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Add Card" /></td>
					</tr>
				</table>
			</form:form>
		</div>

	</div>
	<!-- /container -->
</body>
</html>