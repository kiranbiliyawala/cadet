<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="../../../js/jquery-1.8.2.js"></script>
<script src="../../../js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="../../../css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/InstructionPage.css">
<title><%= session.getAttribute("test_name") %></title>
<link rel="icon" type="image/ico" href="../../../img/favicon.ico">
</head>

<body class="body">
<div class="test_portion">
<h1 class="header">Test :<%= session.getAttribute("test_name") %></h1>
<div class="Time">
Duration : <%= request.getAttribute("TestTime") %>
</div>

<div class="instruction">
<br/>
<br />
<strong>Instructions...</strong>
<br />
1. The Test will be containing of only MCQ.<br />
2. Any cheating or misbehaviour during test might result into strict steps.<br />
3. For any kind of help contact Test supervisors.<br />
</div>
<form method="post" action="Test">
<div class="closestartbtn"><button class="btn butn btn-primary" type="submit" value="Start Test">Start Test</button></div>

</form>

</body>
</html>
