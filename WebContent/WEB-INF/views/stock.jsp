<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Stock</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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
					<a class="navbar-brand" href="index.html">Assignment 4</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="profile.html">${sessionScope.user.userName}</a></li>
						<li><a href="addAccount.html">Add Account</a></li>
						<li><a href="viewCart.html">Cart Items:${cartSize} </a></li>
						<li><a href="${pageContext.request.contextPath}/logout.html">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<div class="jumbotron">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Title</th>
						<th>Manufacturer</th>
						<th>Category</th>
						<th>Rating</th>
						<th>Price</th>
						<th>Add To Cart</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value="${stock.title}" /></td>
						<td><c:out value="${stock.manufacturer}" /></td>
						<td><c:out value="${stock.category.categoryTitle}" /></td>
						<td><c:out value="${stock.rating}" /></td>
						<td><c:out value="€${stock.price}" /></td>
						<td><a href="<c:url value="/addToCart/${stock.id}.html" />"
							class="btn btn-info btn-sm">Add to Cart</a></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="jumbotron">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Review</th>
						<th>Rating</th>
						<th>Date</th>
						<th>User</th>
					</tr>
				</thead>
				<c:forEach items="${stockReviews}" var="review">
					<tbody>
						<tr>
							<td><c:out value="${review.review}" /></td>
							<td><c:out value="${review.rating}" /></td>
							<td><c:out value="${review.date}" /></td>
							<td><c:out value="${review.user.userName}" /></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<br>
			<h2>Add Review</h2>
			<form:form method="POST" commandName="review"
				action="/Assignment4/addReview.html" class="form-signin">
				<form:textarea path="review" class="form-control"
					placeholder="Review" />
				Rating:
				<form:select path="rating" class="form-control" placeholder="Rating">
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					<form:option value="4">4</form:option>
					<form:option value="5">5</form:option>
				</form:select>
				<div class="form-group">
					<input type="submit" value="Submit"
						class="btn btn-large btn-success" />
				</div>
			</form:form>
		</div>


	</div>
	<!-- /container -->


	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
