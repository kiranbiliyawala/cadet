<%@page import="org.cadet.util.model.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/RegisterPage.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="float: left; margin-left: 20%">

<h2 class="heading" align="center">Registration Form</h2>

<form method="post" action="RegisterUser" onsubmit="return validate()">
<table>
<tr>
<td>Email :</td>
<td><input name="Username" type="text"/></td>
<td><i style="color: red;" id="username_error"></i></td>
</tr>

<tr>
<td>Password :</td>
<td><input name="Password" type="password"/></td>
</tr>

<tr>
<td>Confirm Password :</td>
<td><input name="Password2" type="password"/></td>
<td><i style="color: red;" id="password_error"></i></td>
</tr>

<tr>
<td>Name :</td>
<td><input name="Name" type="text"/></td>
<td><i style="color: red;" id="name_error"></i></td>
</tr>

<tr>
<td>Contact :</td>
<td><input name="Contact" type="text"/></td>
<td><i style="color: red;" id="contact_error"></i></td>
</tr>
</table>
<i id="captchaerror" style="color: red;"></i>
<script type="text/javascript"
     src="http://www.google.com/recaptcha/api/challenge?k=<%out.println(Constants.Captcha.publicKey);%>">
  </script>
  <noscript>
     <iframe src="http://www.google.com/recaptcha/api/noscript?k=<%out.println(Constants.Captcha.publicKey);%>"
         height="300" width="400" frameborder="0"></iframe><br>
     <textarea name="recaptcha_challenge_field" id ="recaptcha_challenge_field" rows="3" cols="40">
     </textarea>
     <input type="hidden" name="recaptcha_response_field" id = "recaptcha_response_field"
         value="manual_challenge">
  </noscript>
<input class="register" type="submit" value="Register"/>
<input class="reset" type="reset"/>
</form>
</div>
</body>
</html>