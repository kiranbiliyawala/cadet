<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><script src="../../../js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="../../../css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/InstructionPage.css">
<title><%= session.getAttribute("test_name") %></title>
<link rel="icon" type="image/ico" href="../../../img/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body class="body">
<div class="test_portion">
<h1 class="header">Test :<%= session.getAttribute("test_name") %></h1>


<div class="instruction">
<br/>
TEST FINISHED ...
</div>
<form method="post" action="" onsubmit="window.close()">
<div class="closestartbtn"><button class="btn butn btn-primary" onclick="window.close()" type="button" value="End Test">Close Window</button></div>
</form>

</div>
</body>

</html>