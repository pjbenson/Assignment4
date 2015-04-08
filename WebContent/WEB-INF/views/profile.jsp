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

<!-- Bootstrap core CSS -->
<script type="text/javascript" src="bootstrap/autocomplete.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->
<link href="navbar.css" rel="stylesheet" type="text/css">

<script src="bootstrap/docs/assets/js/ie-emulation-modes-warning.js"
	type="text/javascript"></script>

<script type="text/javascript" src="bootstrap/dist/js/bootstrap.min.js"></script>
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
						<li><a href="">${sessionScope.user.userName}</a></li>
						<li><a href="addAccount.html">Add Account</a></li>
						<li><a href="${pageContext.request.contextPath}/logout.html">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->

		<div class="container">
			<div class="row">
				<div class="col-md-8 col-xs-10">
					<div class="well panel panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12 col-sm-4 text-center"></div>
								<!--/col-->
								<div class="col-xs-12 col-sm-8">
									<h2>Profile</h2>
									<p>
										<strong>Username: ${sessionScope.user.userName}</strong>
									</p>
									<p>
										<strong>Address: ${sessionScope.user.account.address}</strong>
									</p>
									<p>
										<strong>Wallet: </strong><i class="glyphicon glyphicon-euro"></i>
									</p>
								</div>
								<!--/col-->
							</div>
							<!--/row-->
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				<!--/col-->
			</div>
			<!--/row-->
		</div>


		<div class="jumbotron">
			<div class="form-group">
				<label for="sel1">Select list:</label> <select class="form-control"
					id="sel1">
					<option>Category</option>
					<option>Manufacturer</option>
					<option>Title</option>
				</select>
			</div>
			<form>
				<div class="ui-widget">
					<label for="stock">Title: </label> <input id="stock" />
				</div>
			</form>

			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Manufacturer</th>
						<th>Category</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Purchase</th>
					</tr>
				</thead>
				<c:forEach items="${stock}" var="stock">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${stock.title}" /></td>
								<td><c:out value="${stock.manufacturer}" /></td>
								<td><c:out value="${stock.category.categoryTitle}" /></td>
								<td><c:out value="${stock.quantity}"/></td>
								<td><c:out value="€${stock.price}" /></td>
								<td><a href="#" class="btn btn-info btn-sm">Add to Cart</a></td>
							</tr>

						</c:if>
					</tbody>
				</c:forEach>
			</table>
		</div>
		<div class="jumbotron">
			<h2>Search For Books</h2>
			<form:form action="searchBook">
				<label for="searchObject" class="control-label">Search By...</label>
				<select name="searchObject" class="form-control">
					<option value="author">Author</option>
					<option value="title">Title</option>
					<option value="category">Category</option>
				</select>
				<input type="submit" value="Search"
					class="btn btn-large btn-success" />
			</form:form>
		</div>
	</div>
	<!-- /container -->

</body>
</html>