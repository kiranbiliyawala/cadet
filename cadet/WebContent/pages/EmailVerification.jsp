<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>CADET | Verify Email address</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="icon" type="image/ico" href="img/favicon.ico">
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
        <link rel="stylesheet" href="css/bootstrap-responsive.css">
 		<link rel="stylesheet" href="css/homepage.css"> 
        <script src="js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
    </head>
 <body>
 
 	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="brand" href="#"><img id="logo" src="img/cadet.gif" alt="CADET"></img></a>
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
 		<form action="EmailResend" method="post">
 			<div class="hero-unit span10 offset4">
 				<h2>Hey, <%= session.getAttribute("user") %></h2>
 				<input type="hidden" name="username" value="<%= session.getAttribute("user") %>"/>
 				<h3>We've send an email to your email id. Please verify your email id.</h3>
 				<p>If you haven't got the email Click on &nbsp;<input type="submit" class="btn" name="btnsubmit" value="Resend"/></p>
 			</div>
 	</form>
 	</div>
 	
 	
</body>
</html>
