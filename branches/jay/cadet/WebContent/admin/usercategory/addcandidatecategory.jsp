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
<title>Create Test</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="../../css/bootstrap.css">
<link rel="icon" type="image/ico" href="../../img/favicon.ico">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

#footer {
	background: none scroll repeat 0 0 #FFFFFF;
}
</style>
<link rel="stylesheet" href="../../css/bootstrap-responsive.css">
<link rel="stylesheet" href="../../css/main.css">


</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<jsp:include page="/admin/NavBar.jsp"></jsp:include>
	
	<!--/.navbar -->

	<div class="container-fluid">
		<div class="row">
			
<jsp:include page="/admin/Accordian.jsp"></jsp:include>

			<div class="container-fluid span7">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid pull-left">
							<span class="brand">Candidate Category</span>
						</div>
					</div>
				</div>
				<table id="tblusercategories" class="table table-striped table-condensed table-hover">
					<thead>
						<th> Add Candidate Categories</th>
					</thead>
					<tbody>
						<form method="post" action="AddCandidateCategory">
							<tr><td><input type="text" name="txtcandidatecategory"/></td></tr>
							<tr><td><input type="Submit" class="btn" value="Save"/></td></tr>
						</form>
					</tbody>
				</table>
			</div>
		</div>
		<!--/#accordion -->


		<!--/.row div -->

	</div>
	<!--/main container -->

	<jsp:include page="/admin/Footer.jsp"></jsp:include>

	
	<script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
        <script src="../../js/bootbox.js"></script>

	<script type="text/javascript">
	
	$("#category").addClass("active");
	$("#collapse4").addClass("in");
	</script>
	
</body>
</html>
