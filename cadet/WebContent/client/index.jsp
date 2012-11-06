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
        <title>CADET | Dashboard</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="../css/bootstrap.css">
        <link rel="icon" type="image/ico" href="../img/favicon.ico">
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
        <link rel="stylesheet" href="css/main.css">
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
					</div>
					<div class="container-fluid span8">
						<table id="tblTests" class="table table-striped table-condensed table-hover">
							<thead>
								<tr>
									<th>Test Name</th>
									<th>Date</th>
									<th>Duration</th>
									<th>Appear</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>	<!--/.row div -->
        </div>	<!--/.container div -->

		<hr>
		
        <jsp:include page="/client/Footer.jsp"></jsp:include>	
		

        <script src="../js/jquery-1.8.2.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../js/handlebars.js"></script>
        <script type="text/javascript">
    		$("#dashboard").addClass("active");
    	</script>
        <script src="../js/bootbox.js"></script>
        <script id="gettests" type="text/x-handlebars-template">
        	{{#if testList}}
				{{#each testList}}
					<tr>
						<td>{{testName}}</td>
						<td>{{#if testDate}}{{testDate}}{{else}}NA{{/if}}</td>
						<td>{{testDuration}} Mins.</td>
						<td>
							{{#if givetest}}
								<input type="hidden" id="testId" name="testId" value={{testId}}>
								<button class="btn btn-info" onclick="start_test('{{testType}}','{{testId}}')">Appear Test {{givetest}}</button>
							{{else}}
								<input type="hidden" id="testId" name="testId" value={{testId}}>
								<button disabled="disabled" class="btn btn-info">Appear Test {{givetest}}</button>
							{{/if}}
						</td>
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
    	<script src="js/main.js"></script>
    	
    	
    	
    </body>
</html>
