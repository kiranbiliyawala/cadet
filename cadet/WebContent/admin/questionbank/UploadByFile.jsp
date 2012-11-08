<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Question Bank | Add Questions</title>
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
		<script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
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
                	<!--/#accordion -->

				<div class="container-fluid span9 offset*">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container pull-left"><a class="brand" href="#">Upload Question Using File:</a></div>
                        </div>
                    </div>
                    <div>
                    	<form action="/cadet/admin/questionBank/AddQuestionByFile" METHOD="post" ENCTYPE="multipart/form-data">
			                <table>
			                	<tr>
			                		<td style="color:green; font-weight: 600;">
			                			<c:if test="${requestScope.status eq 'success'}">
					                		<script>
											var alertDiv = "<div style=\"position:absolute; margin-top:0.15%;\" class=\"alert alert-success offset4 span4\">You have saved the details <strong>successfully !!!</strong></div>";
											$(document).ready(function(e) {
											setTimeout(function() {
													$("body").prepend(alertDiv);
													setTimeout(function() { $(".alert").alert("close"); },3000);
											},600);
											});
										</script>
					                	</c:if>
					               	</td>
			                	</tr>
			                    <tr>
			                        <td colspan="2" style="height:50px">
			                            <input type="file" name="filename" id="filename" /> <!-- onchange="fileSelected();"  -->
			                        </td>
			                    </tr>
			                    <tr>
			                        <td colspan="2">
			                            <input type="submit" class="btn btn-primary" name="btnUploadQuestion" id="btnUploadQuestion" onclick="return validate()" value="Upload Questions"/>
			                        </td>
			                    </tr>
			                </table>
			           
			              
		                </form>
		                
					</div>
                </div>

            </div>	<!--/.row div -->

        </div>	<!--/.container div -->

		
        <jsp:include page="/admin/Footer.jsp"></jsp:include>

<!--         <script src="../../js/jquery-1.8.2.js"></script> -->
        <script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

        <script src="../../js/bootstrap.js"></script>

        
        <script src="../../js/main.js"></script>
        <script type="text/javascript">
        	function validate(){
        		var file = document.getElementById("filename").value;
        		if(file != null){
        			if(file.substring(file.lastIndexOf(".")+1) == "CSV" || file.substring(file.lastIndexOf(".")+1) == "csv")
        				return true;
        			else 
//         				alert("Upload .CSV files only");
        				var alertDiv = "<div style=\"position:absolute; margin-top:0.15%;\" class=\"alert text-error offset4 span4\"><strong>Upload .CSV files only </strong></div>";
						$(document).ready(function(e) {
								setTimeout(function() {
								$("body").prepend(alertDiv);
								setTimeout(function() { $(".alert").alert("close"); },3000);
						},600);
					});
        				return false;
        		}
        		else{
//         			alert("Select File to Upload");
        			var alertDiv = "<div style=\"position:absolute; margin-top:0.15%;\" class=\"alert text-error offset4 span4\"><strong>Select File to Upload </strong></div>";
					$(document).ready(function(e) {
							setTimeout(function() {
							$("body").prepend(alertDiv);
							setTimeout(function() { $(".alert").alert("close"); },3000);
					},600);
				});
        		}
        	}
        </script>

         <script type="text/javascript">
        $("#question").addClass("active");
        $("#collapse2").addClass("in");
        </script>
        
        
      
     
        	
        
        
	</body>
</html>