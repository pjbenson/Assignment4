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
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Orders</title>

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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
						<li><a href="profile.html">${sessionScope.user.userName}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout.html">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Main component for a primary marketing message or call to action -->

		<div class="jumbotron">
		<h2>User</h2>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Billing Address</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${userForAdmin.userName}</td>
						<td>${userForAdmin.account.address}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="jumbotron">
		<h2>Orders</h2>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Manufacturer</th>
						<th>Category</th>
						<th>Price</th>
					</tr>
				</thead>
				<c:forEach items="${userOrders}" var="order">
					<c:forEach items="${order.lineitems}" var="lineitem">
						<tbody>
							<c:if test="${sessionScope != null}">

								<tr>
									<td><c:out value="${lineitem.stock.title}" /></td>
									<td><c:out value="${lineitem.stock.manufacturer}" /></td>
									<td><c:out value="${lineitem.stock.category.categoryTitle}" /></td>
									<td><c:out value="€${lineitem.stock.price}" /></td>
								</tr>

							</c:if>
						</tbody>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
	</div>
	<!-- /container -->

</body>
</html>