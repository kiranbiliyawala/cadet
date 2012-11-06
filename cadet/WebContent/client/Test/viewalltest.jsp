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
<meta name="viewport" content="width=device-width">
<title>CADET | Register for test</title>
<link rel="stylesheet" href="../../css/bootstrap.css">
 <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
        <link rel="stylesheet" href="../css/main.css">
</head>
<body>
<!--[if lt IE 7]>
<p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
<![endif]-->

		<jsp:include page="/client/NavBar.jsp"></jsp:include>	
			<!--/.navbar -->
        
        <div class="container-fluid">
        	<div class="row">
                <jsp:include page="/client/Accordian.jsp"></jsp:include>	
			<!--/#accordion -->
                <div class="container-fluid span9">
					<div class="navbar">
						<div class="navbar-inner">
							<div class="container-fluid pull-left">
								<a class="brand" href="#">Tests</a>
							</div>
						</div>
						<div>
							<table id="tblTests" class="table table-striped table-condensed table-hover">
								<thead>
								<tr>
									<th>Test Name</th>
									<th>Date</th>
									<th>Duration</th>
									<th>Attempted / All</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							</tbody>			
						</table>											
					</div>						
				</div>
			</div>
           </div>
        </div>
				
		<jsp:include page="/client/Footer.jsp"></jsp:include>	
		
	
		<script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
		<script id="tmpltTests" type="text/x-handlebars-template">
			{{#if tests}}
				{{#each tests}}
				<tr>
					<td> {{testName}} </td>
					<td> {{testDate}} </td>
					<td> {{testDuration}} Mins. </td>
					<td> {{testDesc}}</td>
				</tr>
			{{/each}}
			{{else}}
				<tr>
					<td><p class="text-warning">No Test Available</p></td>
					<td></td>
					<td></td>
					<td></td>		
				</tr>
			{{/if}}
		</script>
	<script src="../js/jsviewalltest.js"></script>
</body>
</html>