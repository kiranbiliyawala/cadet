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
			body {
				display: none;
			}
		</style>
	</noscript>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="./css/bootstrap.css">
	<link rel="icon" type="image/ico" href="img/favicon.ico">
	<style>
		body {
				padding-top: 0px;
				padding-bottom: 40px;
		}

		footer {
			background: none scroll repeat 0 0 #FFFFFF;
		}
	</style>
	<link rel="stylesheet" href="./css/bootstrap-responsive.css">
	<link rel="stylesheet" href="./css/homepage.css">
</head>

<body>
	<div id="wholebody">
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">

					<a class="brand" href="#"><img id="logo" src="./img/cadet.gif"
						alt="CADET"></img></a>
					<ul class="nav" id="ulcontainer">
						<li class="active"><a href="">Home</a></li>
						<li><a class="liheader" href="#features">Features</a></li>
					</ul>
				</div>
				<!--/.container -->
			</div>
		</div>
	</div>
	<!--/.navbar -->
	<div>
		<div id="joinuscontainer">
			<div id="welcometitle">Welcome to CADET</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span2"><button class="btn btn-primary" id="joinus" onclick="transferRegisteration()">Join Us</button></div>
					<div class="span1 offset3" id="verticalline">&nbsp;</div>
					<div class="span3 offset1" id="form">
							<form id="loginform" action="LoginAuthorize" class="form-horizontal" method="POST" onsubmit="return submit_val()">
								<div class="caption">
      									<h2>Login</h2>
  								</div>
  								<div id="horizontalline">
  								</div>
  								<div class="control-group">
    								<label class="control-label" for="username">Email: </label>
    								<div class="controls">
      									<input type="text" id="username" name="username" class="required email" placeholder="Enter your mailid">
    								</div>
  								</div>
  								<div class="control-group">
    								<label class="control-label" for="password">Password: </label>
    								<div class="controls">
      									<input type="password" class="required" minlength='6' name="password" id="password" placeholder="Password">
    								</div>
  								</div>
  								<div class="control-group">
    								<div class="controls">
      									<a href="#">Forgot Password</a>
    								</div>
  								</div>
  								<div class="control-group">
    								<div class="controls">
      									<input type="submit" class="btn" value="Login" id="btnlogin"/>
    								</div>
  								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="features">
		
	</div>
	<footer class="container-fluid navbar navbar-fixed-bottom">
		<hr>
		<div class="row pull-right" style="">
			<a class="span*" href="#">About</a> <a class="span*" href="#">FAQ</a>
			<a class="span*" href="#">Contact us</a> <a class="span*" href="#">Help</a>
			<a class="span*" href="#">Privacy &amp; Policy</a>
		</div>
	</footer>
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/sha.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script>
    
    	function validate(){
    		return $("#loginform").valid();
    	}
    
		function setFocus()
		{
			document.getElementById("username").focus();
		}
	</script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
