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
        <title>CADET | Change Password</title>
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
								<a class="brand" href="#">Change Password</a>							
							</div>
						</div>
							<div class="container-fluid span9">
								<form class="cmxform" id="passwordform" action="UpdateCandidatePassword" method="post" onsubmit="return submit_val_cp()">
            						<table>
            							<tr>
                							<td> <label>New Password</label></td>
                							<td> <input type="password" name="changepassword" id="changepassword" minlength='6' class="required" equalTo="#confirmpassword" value="" placeholder="password"> </td>
            							</tr>
            							<tr>
                							<td><label>Confirm Password</label></td>
                							<td> <input type="password" name="confirmpassword" id="confirmpassword" minlength='6' equalTo="#changepassword" class="required" value="" placeholder="password"></td>
            							</tr>
            							<tr>
                							<td> <button class="btn btn-primary" name="save" type="submit" value="Save">Save</button></td>
                							<td> <a class="btn btn-danger" href="/cadet/client/dashboard">Cancel</a></td>
            							</tr>
          							</table>
          						</form>
							</div>
					</div>
					<div class="container-fluid span8">
						
					</div>
				</div>
			</div>	<!--/.row div -->
        </div>	<!--/.container div -->

		<hr>
		
        <jsp:include page="/client/Footer.jsp"></jsp:include>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/jquery.validate.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
        <script src="../../js/bootbox.js"></script>
        <script src="../../js/sha.js"></script>
        <script src="../js/main.js"></script>
       
    	
    </body>
</html>
