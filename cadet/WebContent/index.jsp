<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>CADET</title>
	<noscript>
		NO JAVASCRIPT !!!
		<style>
		body{
			display: none;
		}
		</style>
	</noscript>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="./css/bootstrap.css">
	<style>
 		body { 
 			padding-top: 50px; 
 			padding-bottom: 40px; 
 		} 

		footer {
			background: none scroll repeat 0 0 #FFFFFF;
		}
/* 		.containe{ */
/* 		position: fixed; */
/* 		left:30%; */
/* 		height: 60%; */
/* 		right:20%; */
/* 		top:10%; */
/* 		bottom: 10%; */
/* 		} */
	</style>
	<link rel="stylesheet" href="./css/bootstrap-responsive.css">
	<!-- <link rel="stylesheet" href="./css/main.css"> -->
	<link rel="stylesheet" href="./css/homepage.css">
	
	
</head>

<body>
	
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="brand" href="#"><img id="logo" src="./img/cadet.gif" alt="CADET"></img></a>
				<ul class="nav" id="ulcontainer">
					<li class="active"><a href="#">Home</a></li>
					<li><a class="liheader" href="#">Features</a></li>
				</ul>
			</div>
			<!--/.container -->
		</div>
	</div>
	<!--/.navbar -->

	<div class="container-fluid">
<!-- 		<div class="row"> -->
			<div class="span6"></div>
			<div class="span10">
				<div id="welcome" style="position: absolute;">
					<div class="hero-unit" id="cadettext">
						Welcome to CADET <br>
						<div id="joinus">
							<button class="btn btn-primary" onclick="transferRegisteration()">Join Us</button>
						</div>
						<span style="font: 60px calibri;">&nbsp;</span>
					</div>
					<div id="hero-unit-support"></div>
					<span style="font: 20px calibri; margin-top: -35px; display:block; position: relative">OR</span>
					<div id="form">
						<form action="LoginAuthorize" method="POST" >
							<table style="margin-left: 90px;">
								<caption>Login</caption>
								<tr>
									<td><label>Username: </label></td>
									<td><input type="text" id="username" name="username" placeholder="Enter your username" /></td>
								</tr>
								<tr>
									<td><label>Password: </label></td>
									<td><input type="password" id="password" name="password" placeholder="Enter Password" /></td>
								</tr>
								<tr>
									<td></td>
									<td><!-- <input type="checkbox" /><span class="loginutils"> Remember me |</span>  --><a href="#" class="loginutils">Forgot Password</a></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" class="btn" id="btnlogin"/></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="span6"></div>
<!-- 		</div> -->
	</div>
	<%	
		
		session.setAttribute("username", request.getParameter("username"));
		
	%>
	<footer class="container-fluid navbar navbar-fixed-bottom">
		<hr>
		<div class="row pull-right" style="0% 10% 10% 0%">
			<a class="span*" href="#">About</a>
			<a class="span*" href="#">FAQ</a>
			<a class="span*" href="#">Contact us</a>
			<a class="span*" href="#">Help</a>
			<a class="span*" href="#">Privacy &amp; Policy</a>
		</div>
	</footer>
	<script src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/sha.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	
	
</body>
</html>
