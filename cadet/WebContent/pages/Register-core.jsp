<%@page import="org.cadet.util.model.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/sha.js"></script>
<script type="text/javascript" src="../js/RegisterPage.js"></script>
<style type="text/css">
.register,.reset{
border: 1px solid black;
background: #1e73ff;
font-size: 20px;
color: white;
padding-top: 5px;
padding-bottom: 5px;
padding-left: 5px;
padding-right: 5px;
position: relative;
margin-left: 20px;
}
.heading{
font-size: 30px;
color : #1e73ff;
font-family: sans-serif;
}
.Fieldnames{
color: gray;
font-size: 20px;
font-family: sans-serif;
position: relative;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="message_body" style="float: left; margin-left: 20%;">

<h2 class="heading" align="center">Registration Form</h2>

<form method="post" action="../RegisterUser" onsubmit="return validate()">
<table>
<tr class="tr">
<td><label class="Fieldnames">Email :</label></td>
<td><input name="Username" id="Username" type="text"/></td>
<td><i style="color: red;" id="username_error"></i></td>
</tr>

<tr class="tr">
<td><label class="Fieldnames">Password :</label></td>
<td><input name="Password" id="Password" type="password"/></td>
</tr>

<tr class="tr">
<td><label class="Fieldnames">Confirm Password :</label></td>
<td><input name="Password2" id="Password2" type="password"/></td>
<td><i style="color: red;" id="password_error"></i></td>
</tr>

<tr class="tr">
<td><label class="Fieldnames">Name :</label></td>
<td><input name="Name" id="Name" type="text"/></td>
<td><i style="color: red;" id="name_error"></i></td>
</tr>

<tr class="tr">
<td><label class="Fieldnames">Contact :</label></td>
<td><input name="Contact" id="Contact" type="text"/></td>
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
  <br>
<input  class="register" type="submit" value="Register"/>
<input  class="reset" type="reset"/>
</form>
</div>
</body>
</html>