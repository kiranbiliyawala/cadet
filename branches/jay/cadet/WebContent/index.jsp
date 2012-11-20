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

<body onload="setFocus()">
	<div id="wholebody">
		
	<jsp:include page="/NavBar.jsp"></jsp:include>
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
      									<input type="text" id="username" name="username" class="required email" placeholder="Enter your email id">
    								</div>
  								</div>
  								<div class="control-group">
    								<label class="control-label" for="password">Password: </label>
    								<div class="controls">
      									<input type="password" class="required" minlength='6' name="password" id="password" placeholder="Password">
    								</div>
  								</div>
  								<div class="control-group">
    								<div class="text-error" style="margin-left : 45%;"><%
  										try{
  											if(!(Boolean)request.getAttribute("AuthCheck")){
  													out.print("Wrong UserName or Password");
  											}
  										}catch(Exception e){
  									
		  								}
  									%></div>
  								</div>
  								<div class="control-group">
    								<div class="controls">
      									<a href="#forgotpassword" role="button" data-toggle="modal">Forgot Password</a>
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
	
	<div id="forgotpassword" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-header">
    		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    		<h3 id="myModalLabel">Forgot Password</h3>
  		</div>
  		<form action="IForgotPassword" method="post" id="forgotpasswordform" onsubmit="return submit_email()">
  		<div class="modal-body">
    			<label>Email: </label>
    			<input type="text" name="username" class="required email" placeholder="Enter your email id">
  		</div>
  		<div class="modal-footer">
    		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    		<button type="submit" class="btn btn-primary">Send</button>
  		</div>
  		</form>
	</div>
	
	<div id="features" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    		<h3 id="myModalLabel">CADET Features</h3>
  		</div>
  			
  		<div class="modal-body">
			<div id="modalTheme">
 				<img src="/img/thetheme.jpg"  width="100%" height="100%"></img>
			</div>
  		</div>
  		<div class="modal-footer">
    		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  		</div>
  		
	</div>
	
	<hr>
		
   <jsp:include page="/Footer.jsp"></jsp:include>
	
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/sha.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script>
    
    	function validate(){
    		return $("#loginform").valid();
    	}
    	
    	function validate2(){
    		return $("#forgotpasswordform").valid();
    	}
    	
		function setFocus()
		{
			document.getElementById("username").focus();
		}
	</script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
