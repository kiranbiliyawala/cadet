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
								<a class="brand" href="#">Change details</a>
							</div>
						</div>
					</div>
					<div class="container-fluid span8">
							<%
// 								System.out.println(request.getAttribute("CandidateProfileData"));
         					   List<String> list = (List<String>) request.getAttribute("CandidateProfileData");
           					   Iterator<String> it = list.iterator();
           					String currentcat=null;
                               //System.out.println("List: " + it);
            				   while (it.hasNext()) {
            					   
              					  String name = it.next();
                				  String contact = it.next();
                				  currentcat = it.next();
							%>
							<form id="detailform" action="UpdateCandidateProfileServlet" method="post" onsubmit="return validate()">
            					<table>
                  					<tr>
                        				<td><label for="Name:">Name</label></td>
                        				<td><input type="Text" class="required" name="name" id="name" placeholder="Name" value="<%=name%>" ></td>
                    				</tr>
									<tr>
                        				<td><label for="Contact">Contact</label></td>
                        				<td> <input type="Text" class="required digits" minlength='10' name="contact" id="contact" placeholder="Contact No." value="<%=contact%>"></td>
                    				</tr>
                    		<%}%>
                    				<tr>
                        				<td><label for="usercategory">User Category</label></td>
                        				<td> <select name="usercategory">
											<%
                               					 List<String> list2 = (List<String>) request.getAttribute("CandidateCategory");
                               					 Iterator<String> it1 = list2.iterator();
                               					 while (it1.hasNext()) {
                                      				 String cat=it1.next();
                                       				 out.print("hh"+cat);
                            				%>
                            			<option <%if(currentcat.equals(cat)) {out.print("selected='selected'");}  %> value="<%=cat%>"><%=cat%></option>
											<%}%>
                           					 </select>
                        				</td>
                       
                       				</tr>
                    				<tr>	
                        				<td> <button class="btn btn-primary" name="save" type="submit" value="Save">Save</button></td>
                        				<td> <a class="btn btn-danger" name="Cancel" href="/cadet/client/index.jsp">Cancel</a></td>
                    				</tr>
                			</table>
        				</form>
					</div>
				</div>
			</div>	<!--/.row div -->
        </div>	<!--/.container div -->

		<hr>
		
        <jsp:include page="/client/Footer.jsp"></jsp:include>	
		

        <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/bootstrap.js"></script>
        <script src="../../js/handlebars.js"></script>
        <script src="../../js/bootbox.js"></script>
        <script src="../../js/jquery.validate.js"></script>
       
    	<script src="../js/main.js"></script>
    	<script type="text/javascript">
    	function validate(){
    	return	$("#detailform").valid();
    	}
    	</script>
    </body>
</html>
