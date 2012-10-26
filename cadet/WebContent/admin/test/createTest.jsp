<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Create Test</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="../../css/bootstrap.css">
        <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
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

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="../index.jsp"><img src="../../img/cadet.png" alt="CADET"></a>
                    <div class="nav-collapse collapse">
                       <ul class="nav">
                           <li class="active"><a href="../index.jsp">Home</a></li>
                           <li><a href="#questionBank">Question Bank</a></li>
                           <li><a href="testManagement.jsp">Test Management</a></li>
                           <li><a href="#candidateCategory">Candidate Category</a></li>
                       </ul>
                       <button class="btn btn-danger pull-right">Logout</button>
                    </div>
                </div>	<!--/.container -->
            </div>
        </div>	<!--/.navbar -->
        
        <div class="container">
        	<div class="row">
                <div class="accordion span2" id="accordionMenu">

                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-parent="#accordionMenu" href="../index.jsp">Home</a>
                        </div>
                    </div>
    
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse2">Question Bank</a>
                        </div>
                        <div id="collapse2" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <ul class="nav">
                                    <li><a href="#">Add/View Questions</a></li>
                                    <li><a href="#">Upload Questions</a></li>
                                    <li><a href="#">Add/View Category</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse3">Test Management</a>
                        </div>
                        <div id="collapse3" class="accordion-body collapse in">
                            <div class="accordion-inner">

                                <ul class="nav">
                                    <li><a href="testManagement.jsp">Create/View Test</a></li>
                                    <li><a href="#result">Result</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
    
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse4">Candidate Category</a>
                        </div>
                        <div id="collapse4" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <ul class="nav">
                                    <li><a href="#">Add Category</a></li>
                                    <li><a href="#">Edit Category</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>	<!--/#accordion -->

				<div class="container span9 offset*">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container pull-left"><span class="brand">Create Test</span></div>
                        </div>
                    </div>
                    <div class="container span8">
						<form id="frmCreateTest" class="form-horizontal" method="post" action="TestManagement">
							<div class="control-group">
	                            <label class="control-label" for="txtTestName">Test Name : </label>
	                            <div class="controls">
	                            	<input type="text" id="txtTestName" name="txtTestName" placeholder="Test Name" required minlength=2 maxlength=30>
	                            </div>
	                        </div>

							<div class="control-group">
								<label class="control-label" for="optTestType">Type : </label>
								<div class="controls">
		                            <select id="optTestType" name="optTestType">
		                                <option>Adaptive</option>
		                                <option>Non-Adaptive</option>
		                            </select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="taTestDesc">Description : </label>
								<div class="controls">
									<textarea id="taTestDesc" name="taTestDesc" class="span5 nic_edit" style="resize:none;" rows="5" placeholder="Test Description & Instructions"></textarea>
								</div>
							</div>
							
							<div class="control-group">
								<hr class="span8">
							</div>

							<div class="control-group">
								<div class="controls span4">
									<input class="btn btn-primary pull-left" type="submit" value="Create">
									<a href="testManagement.jsp" class="btn btn-danger pull-right">Cancel</a>
								</div>
							</div>

                            <input id="requestType" type="hidden" name="requestType" value="createTest">
                        </form>
                    </div>	<!-- /form container -->
                </div>	<!-- /content container -->

            </div>	<!--/.row div -->

        </div>	<!--/main container -->

		<hr>
        <footer>
                <div class="container row">
                    <a class="span pull-right" href="#">About</a>
                    <a class="span pull-right" href="#">FAQ</a>
                    <a class="span pull-right" href="#">Contact us</a>
                    <a class="span pull-right" href="#">Help</a>
                    <a class="span pull-right" href="#">Privacy &amp; Policy</a>
                </div>
        </footer>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/jquery.validate.js"></script>
        <script src="../../js/additional-methods.js"></script>
        <script src="../../js/nicEdit.js"></script>
        <script src="../../js/jsCreateTest.js"></script>
    </body>
</html>
