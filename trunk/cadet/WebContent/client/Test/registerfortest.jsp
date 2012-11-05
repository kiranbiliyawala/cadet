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

		<div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#home"><img id="logo" src="../../img/cadet.gif" alt="CADET"></a>
                    <div class="nav-collapse collapse">
                       <ul class="nav">
                          <li><a href="client/dashboard">Home</a></li>
                           <li class="active"><a href="/cadet/client/Test/registerfortest.jsp">Test</a></li>
                           <li><a href="/cadet/client/Test/viewalltest.jsp">Profile</a></li>
                       </ul>
                       <button class="btn btn-danger pull-right">Logout</button>
                    </div>
                </div>	<!--/.container -->
            </div>
        </div>	<!--/.navbar -->
        
        <div class="container-fluid">
        	<div class="row">
                <div class="accordion span2" id="accordionMenu">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-parent="#accordionMenu" href="/cadet/client/index.jsp">Home</a>
                        </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                           <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse2">Test</a>
                    </div>
                    <div id="collapse2" class="accordion-body collapse in">
                    	<div class="accordion-inner">
                        	<ul class="nav">
                            	<li><a href="/cadet/client/Test/registerfortest.jsp">Register for Test</a></li>
                                <li><a href="/cadet/client/Test/viewalltest.jsp">View All Test</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
    	
                <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse3">Profile</a>
                        </div>
                        <div id="collapse3" class="accordion-body collapse">
                            <div class="accordion-inner">

                                <ul class="nav">
                                  <li><a href="/cadet/client/profile/changedetails.jsp">Change Details</a></li>
                                    <li><a href="/cadet/client/profile/changepassword.jsp">Change Password</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>	<!--/#accordion -->
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
		<script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
		 <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
		<script id="tmpltTests" type="text/x-handlebars-template">
			{{#if tests}}
				{{#each tests}}
					<tr>
						<td>{{testName}}</td>
						<td>{{testDate}}</td>
						<td>{{testDuration}} Mins.</td>
						<td><button id='btnTest{{testId}}' onclick='registerForTest(this,{{testId}})' class="btn btn-Primary">Register</Button></td>			
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
	<script src="../js/jsregisterfortest.js"></script>
</body>
</html>