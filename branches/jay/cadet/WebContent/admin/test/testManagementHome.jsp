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
<title>CADET | Test Management</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="../../css/bootstrap.css">
<link rel="icon" type="image/ico" href="../../img/favicon.ico">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

footer {
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

	<jsp:include page="/admin/NavBar.jsp"/>
	<!-- /.navbar -->

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/admin/Accordian.jsp"/>
			<!-- /#accordion -->

			<div class="container-fluid span9">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid pull-left">
							<a class="brand" href="#">Tests</a>
						</div>
						<div class="container-fluid pull-right">
							<a href="createTest.jsp" class="btn btn-primary">Create Test</a>
						</div>
					</div>
				</div>
				<div class="container-fluid span8">
					<table id="tblTests" class="table table-striped table-condensed table-hover">
						<thead>
							<tr>
								<th>Test Name</th>
								<th>Date</th>
								<th>Duration</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<!--/.row div -->

	</div>
	<!--/.container div -->


	<jsp:include page="/admin/Footer.jsp"/>

	<script src="../../js/jquery-1.8.2.js"></script>
	<script src="../../js/bootstrap.js"></script>
	<script src="../../js/bootbox.js"></script>
	<script src="../../js/handlebars.js"></script>

	<script id="tmpltTests" type="text/x-handlebars-template">

			{{#if testList}}
				{{#each testList}}
					<tr>
						<td>{{testName}} <small><em class="text-info">({{testType}})</em></small></td>
						<td>{{#if testDate}}{{testDate}}{{else}}NA{{/if}}</td>
						<td>{{testDuration}} Mins.</td>
						<td>
							<form style="margin:0px" method="post" action="TestManagement">
								<input type="hidden" id="testId" name="testId" value={{testId}}>
								<input type="hidden" id="requestType" name="requestType" value="editTest">
								<input type="submit" class="btn btn-info" value="Edit">
							</form>
						</td>
						<td><a href="#" id={{#deleteButton}}{{testId}}{{/deleteButton}} class="btn btn-danger btnDelete">Delete</a></td>
					</tr>
				{{/each}}
			{{else}}
				<tr>
					<td><p class="text-warning">No Test Available</p></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			{{/if}}
	</script>

	<script src="../js/jsGlobal.js"></script>
	<script src="../js/jsTestManagementHome.js"></script>
		<script type="text/javascript">
        $("#test").addClass("active");
        $("#collapse3").addClass("in");
        </script>
</body>
</html>
