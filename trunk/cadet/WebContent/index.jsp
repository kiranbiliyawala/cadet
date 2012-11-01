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
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="./css/bootstrap.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

#accordionMenu {
	position: fixed;
}

footer {
	background: none scroll repeat 0 0 #FFFFFF;
}
</style>
<link rel="stylesheet" href="./css/bootstrap-responsive.css">
<link rel="stylesheet" href="./css/main.css">
<link rel="stylesheet" href="./css/homepage.css">

</head>

<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">

				<a class="brand" href="#"><img id="logo" src="./img/cadet.gif"
					alt="CADET"></img></a>

				<ul class="nav" id="ulcontainer">
					<li class="active"><a href="#">Home</a></li>
					<li><a class="liheader" href="#">Features</a></li>
				</ul>
			</div>
			<!--/.container -->
		</div>
	</div>
	<!--/.navbar -->

	<div class="container">
		<div class="row">

			<div id="welcome" class="container">
				<div class="hero-unit">
					Welcome to CADET ! <br>
					<div id="joinus">
						<button class="btn btn-primary">Join Us</button>
					</div>
					<span style="font: 60px calibri;">&nbsp;</span>
				</div>
				<div id="hero-unit-support"></div>
				<span style="font: 60px calibri; margin-top: -39px; display:block; position: relative">OR</span>
				<div id="form">
					<form action="" method="post">
						<table align="center">
							<caption>Login</caption>
							<tr>
								<td>Username:</td>
								<td><input type="text" id="txtusername"
									placeholder="Enter your username" /></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type="password" id="txtpassword"
									placeholder="Password" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="checkbox" /><span class="loginutils">
										Remember me</span> <a href="#" class="loginutils">Forget Password</a>
								</td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" class="btn"
									id="btnlogin" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>

	<footer class="container navbar navbar-fixed-bottom">
		<hr>
		<div class="row" style="margin-bottom: 10px;">
			<a class="span pull-right" href="#">About</a> <a
				class="span pull-right" href="#">FAQ</a> <a class="span pull-right"
				href="#">Contact us</a> <a class="span pull-right" href="#">Help</a>
			<a class="span pull-right" href="#">Privacy &amp; Policy</a>
		</div>
	</footer>

	<script src="./js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</body>
</html>