<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
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
        <title>CADET | Change Details</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="../../css/bootstrap.css">
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
                        <li><a href="/cadet/client/index.jsp">Home</a></li>
                           <li><a href="/cadet/client/Test/registerfortest.jsp">Test</a></li>
                           <li class="active"><a href="/cadet/client/Test/viewalltest.jsp">Profile</a></li>
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
                        <div id="collapse2" class="accordion-body collapse">
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
                            <div class="accordion-inner in">

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
								<a class="brand" href="#">Change details</a>
							</div>
						</div>
					</div>
					<div class="container-fluid span8">
							<%
         					   List<String> list = (List<String>) request.getAttribute("CandidateProfileData");
           					   Iterator it = list.iterator();
            				   while (it.hasNext()) {
              
              					  String name = (String) it.next();
                				  String contact = (String) it.next();
							%>
							<form action="GetCandidateProfileServlet" method="post">
            					<table>
                  					<tr>
                        				<td><label for="Name:">Name</label></td>
                        				<td><input type="Text" name="Name :" value="<%=name%>" ></td>
                    				</tr>
									<tr>
                        				<td><label for="Contact">Contact</label></td>
                        				<td> <input type="Text" name="Contact :" value="<%=contact%>"></td>
                    				</tr>
                    		<%}%>
                    				<tr>
                        				<td><label for="usercategory">User Category</label></td>
                        				<td> <select name="usercategory">
											<%
                               					 List<String> list2 = (List<String>) request.getAttribute("CandidateCategory");
                               					 Iterator it1 = list2.iterator();
                               					 while (it1.hasNext()) {
                                      				 String cat=(String)it1.next();
                                       				 out.print("hh"+cat);
                            				%>
                            			<option value="<%=cat%>"><%=cat%></option>
											<%}%>
                           					 </select>
                        				</td>
                       
                       				</tr>
                    				<tr>	
                        				<td> <button name="save" type="submit" value="Save">Save</button></td>
                        				<td> <a name="back" href="	./index.jsp">Back</a></td>
                    				</tr>
                			</table>
        				</form>
					</div>
				</div>
			</div>	<!--/.row div -->
        </div>	<!--/.container div -->

		<hr>
		
        <footer>
                <div class="container-fluid row">
                    <a class="span pull-right" href="#">About</a>
                    <a class="span pull-right" href="#">FAQ</a>
                    <a class="span pull-right" href="#">Contact us</a>
                    <a class="span pull-right" href="#">Help</a>
                    <a class="span pull-right" href="#">Privacy &amp; Policy</a>
                </div>
        </footer>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
        <script src="../../js/bootbox.js"></script>
       
    	<script src="../js/main.js"></script>
    </body>
</html>
