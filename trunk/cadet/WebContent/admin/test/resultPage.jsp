<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>CADET | Result</title>
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

<script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
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
							<span class="brand"><c:out value="${testName}"></c:out>&nbsp;Score</span>
						</div>
						<div class="container-fluid pull-right">
							<button class="btn btn-primary">Save as PDF</button>
						</div>
					</div>
				</div>
				<div class="container-fluid span8">
					<table id="tblResults" class="table table-striped table-condensed table-hover">
							<thead>
								<tr>
									<th>Candidate Name</th>
									<th>Score</th>
									<th>Attempted</th>
									<th>Correct</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
				</div>
				<!-- /form container -->
			</div>
			<!-- /content container -->

		</div>
		<!--/.row div -->

	</div>
	<!--/main container -->

	<hr>
		
   <jsp:include page="/admin/Footer.jsp"></jsp:include>

	<script src="../js/jquery-1.8.2.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../js/handlebars.js"></script>
        <script type="text/javascript">
    		$("#dashboard").addClass("active");
    		$("#collapse3").addClass("in");
    	</script>
        <script src="../js/bootbox.js"></script>
        <script id="getresults" type="text/x-handlebars-template">
        	{{#if resultList}}
				{{#each resultList}}
					<tr>
						<td>{{UserName}}</td>
						<td>{{score}}</td>
						<td>{{attempted}} Mins.</td>
						<td>{{correct}}</td>
					</tr>
				{{/each}}
			{{else}}
				<tr>
					<td><p class="text-warning">No Data Available</p></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			{{/if}}
    	</script>
    	<script src="../js/jstestresults.js"></script>
</body>
</html>
