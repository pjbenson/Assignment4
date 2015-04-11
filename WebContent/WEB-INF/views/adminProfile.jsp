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

<title>Admin Profile</title>

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
						<li><a
							href="${pageContext.request.contextPath}/addCategory.html">Add
								Category</a></li>
						<li><a
							href="${pageContext.request.contextPath}/addStock.html">Add
								Stock</a></li>
						<li><a href="${pageContext.request.contextPath}/logout.html">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->

		<div class="jumbotron">
			<h2>Users</h2>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>User ID</th>
						<th>Username</th>
						<th>Billing Address</th>
						<th>Delete</th>
					</tr>
				</thead>
				<c:forEach items="${users}" var="user">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.userName}" /></td>
								<td><c:out value="${user.account.address}" /></td>
								<td><a href="<c:url value="/delete/${user.id}.html" />"
									class="btn btn-info btn-sm">Delete</a></td>
							</tr>

						</c:if>
					</tbody>
				</c:forEach>
			</table>
		</div>

		<div class="jumbotron">
			<h2>All Stock</h2>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Manufacturer</th>
						<th>Category</th>
						<th>Price</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<c:forEach items="${stock}" var="stock">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${stock.title}" /></td>
								<td><c:out value="${stock.manufacturer}" /></td>
								<td><c:out value="${stock.category.categoryTitle}" /></td>
								<td><c:out value="€${stock.price}" /></td>
								<td><c:out value="${stock.quantity}" /></td>
							</tr>

						</c:if>
					</tbody>
				</c:forEach>
			</table>
		</div>

		<div class="jumbotron">
			<h2>Search for Stock Items</h2>
			<form:form method="POST" commandName="adminSearchObject"
				action="/Assignment4/adminSearchObject.html">
				<label for="adminSearchObject" class="control-label">Search By...</label>
				<form:select path="searchBy" id="searchBy" class="form-control">
					<form:option value="manufacturer">Manufacturer</form:option>
					<form:option value="title">Title</form:option>
					<form:option value="category">Category</form:option>
				</form:select>
				<form:input path="searchString" id="searchString"
					class="form-control" placeholder="Search" />
				<input type="submit" value="Search"
					class="btn btn-large btn-success" />
			</form:form>
			<hr>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Manufacturer</th>
						<th>Category</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>
				</thead>
				<c:forEach items="${adminSortedStock}" var="stock">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${stock.title}" /></td>
								<td><c:out value="${stock.manufacturer}" /></td>
								<td><c:out value="${stock.category.categoryTitle}" /></td>
								<td><c:out value="${stock.quantity}" /></td>
								<td><c:out value="€${stock.price}" /></td>
							</tr>

						</c:if>
					</tbody>
				</c:forEach>
			</table>
		</div>


	</div>
	<!-- /container -->

</body>
</html>