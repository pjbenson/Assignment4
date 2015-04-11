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

<link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template -->

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
						<li><a href="viewCart.html">Cart Items:${cartSize} </a></li>
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
										<strong>Shpping Address:
											${sessionScope.user.account.address}</strong>
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
			<h2>Search for Items in Ascending or Descending Order</h2>
			<form:form method="POST" commandName="ascOrDesc"
				action="/Assignment4/searchbyAscOrDesc.html">
				<label for="searchObject" class="control-label">Search By...</label>
				<form:select path="searchBy" id="searchBy" class="form-control">
					<form:option value="manufacturer">Manufacturer</form:option>
					<form:option value="title">Title</form:option>
					<form:option value="category">Category</form:option>
					<form:option value="price">Price</form:option>
				</form:select>
				<label>Order...</label>
				<form:select path="searchString" id="searchString"
					class="form-control">
					<form:option value="ascending">Ascending</form:option>
					<form:option value="descending">Descending</form:option>
				</form:select>
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
						<th>Add To Cart</th>
					</tr>
				</thead>
				<c:forEach items="${sortedStock}" var="stock">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${stock.title}" /></td>
								<td><c:out value="${stock.manufacturer}" /></td>
								<td><c:out value="${stock.category.categoryTitle}" /></td>
								<td><c:out value="${stock.quantity}" /></td>
								<td><c:out value="€${stock.price}" /></td>
								<td><a href="<c:url value="/addToCart/${stock.id}.html" />"
									class="btn btn-info btn-sm">Add to Cart</a></td>
							</tr>

						</c:if>
					</tbody>
				</c:forEach>
			</table>
		</div>


		<div class="jumbotron">
			<h2>Search for Items By Category, Maunfacturer or Title</h2>
			<form:form method="POST" commandName="searchObject"
				action="/Assignment4/searchObject.html">
				<label for="searchObject" class="control-label">Search By...</label>
				<form:select path="searchBy" id="searchBy" class="form-control">
					<form:option value="manufacturer">Manufacturer</form:option>
					<form:option value="title">Title</form:option>
					<form:option value="category">Category</form:option>
				</form:select>
				<label>Search...</label>
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
						<th>Add To Cart</th>
					</tr>
				</thead>
				<c:forEach items="${unSortedStock}" var="stock">
					<tbody>
						<c:if test="${sessionScope != null}">

							<tr>
								<td><c:out value="${stock.title}" /></td>
								<td><c:out value="${stock.manufacturer}" /></td>
								<td><c:out value="${stock.category.categoryTitle}" /></td>
								<td><c:out value="${stock.quantity}" /></td>
								<td><c:out value="€${stock.price}" /></td>
								<td><a href="<c:url value="/addToCart/${stock.id}.html" />"
									class="btn btn-info btn-sm">Add to Cart</a></td>
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