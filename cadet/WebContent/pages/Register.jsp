<%@page import="org.cadet.util.model.Constants"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]> <html class="no-js"> <![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>CADET | Register</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<script type="text/javascript">
	var RecaptchaOptions = {
		theme : 'clean'
	};
</script>
<link rel="stylesheet" href="css/bootstrap.css" />

<link rel="icon" type="image/ico" href="img/favicon.ico">
<style>
body {
	padding-bottom: 40px;
}

#accordionMenu {
	position: fixed;
}

footer {
	background: none scroll repeat 0 0 #FFFFFF;
}
</style>

<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/homepage.css" />
<script src="js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">

					<a class="brand" href="/cadet/Home"><img id="logo" src="img/cadet.gif"
						alt="CADET"></img></a>
					<ul class="nav" id="ulcontainer">
						<li class="active"><a href="">Home</a></li>
						<li><a class="liheader" href="#features">Features</a></li>
					</ul>
				</div>
				<!--/.container -->
			</div>
		</div>
	<!--/.navbar --> -->

	<div class="container-fluid">
		<div class="row" id="registrationform">
			<form id="regform" method="post" action="RegisterUser"
				onsubmit="return validate()">
				<table>
					<tr>
						<td colspan="2" id="registrationtitle">Registration Form</td>
					</tr>
					<tr class="tr">
						<td><label class="Fieldnames">Email :</label></td>
						<td><input class="required email" name="Username" id="Username" placeholder="User Email ID" type="text" /></td>
						<td><i style="color: red;" id="username_error"></i></td>
					</tr>

					<tr class="tr">
						<td><label class="Fieldnames">Password :</label></td>
						<td><input class="required" minlength='6' equalTo="#Password2" name="Password" id="Password" placeholder="Password" type="password" /></td>
					</tr>

					<tr class="tr">
						<td><label class="Fieldnames">Confirm Password :</label></td>
						<td><input class="required" minlength='6' equalTo="#Password" name="Password2" id="Password2" placeholder="Confirm Password" type="password" /></td>
					</tr>

					<tr class="tr">
						<td><label class="Fieldnames">Name :</label></td>
						<td><input class="required" name="Name" id="Name"
							placeholder="Name" type="text" /></td>
					</tr>

					<tr class="tr">
						<td><label class="Fieldnames">Contact :</label></td>
						<td><input class="required digits" minlength='10' placeholder="Contact No." name="Contact" id="Contact" type="text" /></td>
					</tr>

					<tr class="tr">
						<td><label class="Fieldnames">User Category :</label></td>
						<td><select name="Category" id="usercategory">
								<option>1</option>
						</select></td>
						<td><i style="color: red;" id="contact_error"></i></td>
					</tr>

					<tr>
						<td></td>
						<td><i id="captchaerror" style="color: red;"></i> <script
								type="text/javascript"
								src="http://www.google.com/recaptcha/api/challenge?k=<%out.print(Constants.Captcha.publicKey);%>">
							
						</script>
							<noscript>
								<iframe
									src="http://www.google.com/recaptcha/api/noscript?k=<%out.print(Constants.Captcha.publicKey);%>"
									height="300" width="400"></iframe>
								<br>
								<textarea name="recaptcha_challenge_field"
									id="recaptcha_challenge_field" rows="3" cols="40">
     							</textarea>
								<input type="hidden" name="recaptcha_response_field"
									id="recaptcha_response_field" value="manual_challenge">
							</noscript></td>
					</tr>

					<tr class="tr">
						<td></td>
						<td style="margin-left: 10%;"><input class=" btn register"
							type="submit" value="Register" style="margin: 2%;" /> <input
							class="btn reset" type="reset" style="margin: 2%;" /></td>
					</tr>
				</table>

				<br>
			</form>
		</div>
	</div>
	<footer class="container-fluid navbar navbar-fixed-bottom">
		<hr>
		<div class="row pull-right" style="">
			<a class="span*" href="#">About</a> <a class="span*" href="#">FAQ</a>
			<a class="span*" href="#">Contact us</a> <a class="span*" href="#">Help</a>
			<a class="span*" href="#">Privacy &amp; Policy</a>
		</div>
</footer>
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/sha.js"></script>
	<script type="text/javascript" src="js/RegisterPage.js"></script>
</body>
</html>
