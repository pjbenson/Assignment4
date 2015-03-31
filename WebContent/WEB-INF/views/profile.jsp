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

<!-- Latest compiled and minified CSS --><style type="text/css">
footer {
	margin-top: 20px;
	padding-top: 20px;
	padding-bottom: 20px;
	background-color: #efefef;
}

.container{width: 0 auto;
margin: 0 auto;
margin-left: 250px;}

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
<link href="navbar.css" rel="stylesheet" type="text/css">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="bootstrap/docs/assets/js/ie-emulation-modes-warning.js"
	type="text/javascript"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
									<div class="col-xs-12 col-sm-4 text-center">
										<img src="bootstrap/img/photo.jpg" alt=""
											class="center-block img-circle img-thumbnail img-responsive">
										<ul class="list-inline ratings text-center" title="Ratings">
											<li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
											<li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
											<li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
											<li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
											<li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
										</ul>
									</div>
									<!--/col-->
									<div class="col-xs-12 col-sm-8">
										<h2>${sessionScope.user.userName}</h2>
										<p>
											<strong>Email: </strong>
										</p>
										<p>
											<strong>Age: </strong>
										</p>
										<p>
											<strong>Wallet: </strong><i class="glyphicon glyphicon-euro"></i>
										</p>
										<p>
											<strong>Strategies: </strong>
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
			<!--/container-->
		

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