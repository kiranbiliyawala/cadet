<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>CADET | Dashboard</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>CADET | Home</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">


<link rel="stylesheet" href="../css/bootstrap.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

footer {
	background: none scroll repeat 0 0 #FFFFFF;
}
</style>
<link rel="stylesheet" href="../css/bootstrap-responsive.css">
<link rel="stylesheet" href="../css/main.css">

<script src="../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<jsp:include page="/admin/NavBar.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">

<jsp:include page="/admin/Accordian.jsp"></jsp:include>

			<div class="container-fluid span9">
				<div class="navbar">
						<div class="navbar-inner">
							<div class="container-fluid pull-left">
								<a class="brand" href="#">Tests</a>
							</div>
						</div>
					</div>
				<div class="container-fluid span8">
				<jsp:include page="admindashboard.jsp"></jsp:include>
				</div>
			</div>

		</div>
		<!--/.row div -->

	</div>
	<!--/.container div -->

	<jsp:include page="/admin/Footer.jsp"></jsp:include>

	<script src="../js/jquery-1.8.2.js"></script>
	<script src="../js/bootstrap.js"></script>
	
	<script type="text/javascript">
	$("#dashboard").addClass("active");	
	</script>
</body>
</html>
