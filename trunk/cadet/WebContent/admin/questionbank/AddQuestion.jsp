<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Question Bank | Add/Edit Question</title>
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

		<jsp:include page="/admin/NavBar.jsp"></jsp:include>
		
		<div class="container-fluid">
        	<div class="row-fluid">
                
		<jsp:include page="/admin/Accordian.jsp"></jsp:include>
		<!--/#accordion -->
				<div class="container-fluid span9 offset*">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container-fluid pull-left">
                            	<a class="brand" href="#">Add Question - Category <font style="text-transform: uppercase; font-weight: 700;"><%= session.getAttribute("categoryName").toString() %></font></a>
                            </div>
                        </div>
                    </div>
                    <div>
                    	<% int count = 0;%>
                    	<form action="" id="frmAddQuestion" name="frmAddQuestion"> 
                    	<input type="submit" id="btnSubmit" name="btnSubmit" style="display:none;" class=""/>
                    	<table>
                    		<tr>
                    			<td id="tdNotification" style="display: none; color:green;">
									<%=count %> - Question Added...
									<input type="hidden" id="hdnCategoryId" name="hdnCategoryId" value=""/>			                    				
                    			</td>
                    		</tr>
		                    <tr>
		                        <td colspan="5" style="width:70%;">
		                        	Question:<br/>
		                        	<textarea id="txtQuestion" name="txtQuestion" style="width:800px" rows="4"></textarea>
								</td>
		                    </tr>
		                    <tr>
			                    <td id="divQuestion" align="left" style="height: 30px; color:red;" colspan="5">
									
			                    </td>
			                </tr>
		                    <tr>
		                        <td style="width:250px">
		                        	A.
		                        	<input type="radio" id="radOptionA" name="radOption" value="txtOptionA" style="vertical-align:baseline;" checked="checked"/>
		                        	<input type="text" id="txtOptionA" name="txtOptionA"/>
		                        </td>
		                        <td id="divOptionA" style="width:150px; color:red;" align="left">
		                        </td>
		                        <td align="right" rowspan="4" style="width:100px;">Set Level:</td>
		                        <td align="left" rowspan="4" style="width:200px">
		                            <select id="selLevel" onChange="selLevelSelectionChanged()">
		                            	<option selected="selected" style="color: #cdc9c9;" disabled="disabled"><i>Select Level</i></option>
		                                <option>1</option>
		                                <option>2</option>
		                                <option>3</option>
		                                <option>4</option>
		                                <option>5</option>
		                                <option>6</option>
		                                <option>7</option>
		                                <option>8</option>
		                                <option>9</option>
		                                <option>10</option>
		                            </select>
		                        </td>    
		                        <td id="divSelLevel" style="color: red;" rowspan="4">
		                        </td>
		                    </tr>
                    		<tr>
		                        <td>
		                            B.
		                            <input type="radio" id="radOptionB" name="radOption" value="txtOptionB" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionB" name="txtOptionB" value=""/>
								</td>
		                        <td id="divOptionB" style="width:150px; color:red;" align="left" colspan="4">		                        	
		                        </td>
	                        </tr>
	                        <tr>
		                        <td>
		                            C.
		                            <input type="radio" id="radOptionC" name="radOption" value="txtOptionC" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionC" name="txtOptionC" value=""/>
								</td>
		                        <td id="divOptionC" style="width:150px; color:red;" align="left" colspan="4">
		                        </td>
	                        </tr>
	                        <tr>
		                        <td>
		                            D.
		                            <input type="radio" id="radOptionD" name="radOption" value="txtOptionD" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionD" name="txtOptionD" value=""/>
								</td>
		                        <td id="divOptionD" style="width:150px; color:red;" align="left" colspan="4">
		                        </td>
		                    </tr>
		                    <tr style="height:100px">
		                        <td colspan="5" align="center">
		                        	<input type="button" class="btn btn-primary" id="btnSaveQuestion" name="btnSaveQuestion" onClick="changeAction('SaveQuestion', <%= count %>)" value="Save Question" />
		                        	&nbsp;&nbsp;&nbsp;
		                        	<a href="ViewQuestion" class="btn btn-danger" id="btnBack" name="btnBack" >Back</a>
		                        </td>
		                    </tr>
		                </table>
		                </form>
					</div>
                </div>

            </div>	<!--/.row div -->

        </div>	<!--/.container div -->

		
		<jsp:include page="/admin/Footer.jsp"></jsp:include>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

        <script src="../../js/bootstrap.js"></script>

        <script src="../../js/main.js"></script>
        
        <script type="text/javascript">
        
        	var count = 0
	    	function getCheckedRadio(radio_group) {
	    	    for (var i = 0; i < radio_group.length; i++) {
	    	        var button = radio_group[i];
	    	        if (button.checked) {
	    	            return button;
	    	        }
	    	    }
	    	    return undefined;
	    	}
	    	
        	function selLevelSelectionChanged(){
    			var elementOption = document.getElementById("selLevel");
    			var level = elementOption.options[elementOption.selectedIndex].text;
    			if(level == 'Select Level')
    				document.getElementById("divSelLevel").innerHTML = "Select level for question";
    			else
    				document.getElementById("divSelLevel").innerHTML = "";
        	}

        	function validate( val ){
	    		if(val == 'SaveQuestion'){
	    			if(document.getElementById("txtQuestion").value.toString().length == 0)
	    				document.getElementById("divQuestion").innerHTML = "Question cannot be blank";
	    			else
	    				document.getElementById("divQuestion").innerHTML = "";
	    			
	    			if(document.getElementById("txtOptionA").value.toString().length == 0)
	    				document.getElementById("divOptionA").innerHTML = "Option cannot be blank";
	    			else
	    				document.getElementById("divOptionA").innerHTML = "";
	    			
	    			if(document.getElementById("txtOptionB").value.toString().length == 0)
	    				document.getElementById("divOptionB").innerHTML = "Option cannot be blank";
	    			else
	    				document.getElementById("divOptionB").innerHTML = "";
	    			
	    			if(document.getElementById("txtOptionC").value.toString().length == 0)
	    				document.getElementById("divOptionC").innerHTML = "Option cannot be blank";
	    			else
	    				document.getElementById("divOptionC").innerHTML = "";
	    			
	    			if(document.getElementById("txtOptionD").value.toString().length == 0)
	    				document.getElementById("divOptionD").innerHTML = "Option cannot be blank";
	    			else
	    				document.getElementById("divOptionD").innerHTML = "";

	    			var elementOption = document.getElementById("selLevel");
	    			var level = elementOption.options[elementOption.selectedIndex].text;
	    			if(level == 'Select Level')
	    				document.getElementById("divSelLevel").innerHTML = "Select level";
	    			else
	    				document.getElementById("divSelLevel").innerHTML = "";
    			}
    		}

	    	function changeAction( action, cnt ){
        		if(action == 'SaveQuestion'){
        			validate(action);
        			if(
        					document.getElementById("divQuestion").innerHTML.toString().length == 0 &&
        					document.getElementById("divOptionA").innerHTML.toString().length == 0 &&
        					document.getElementById("divOptionB").innerHTML.toString().length == 0 &&
        					document.getElementById("divOptionC").innerHTML.toString().length == 0 &&
        					document.getElementById("divOptionD").innerHTML.toString().length == 0 &&
        					document.getElementById("divSelLevel").innerHTML.toString().length == 0
        			){
	        			var checkedOption = getCheckedRadio(document.forms[0].elements.radOption);
	        			var question = document.getElementById('txtQuestion').value;
	        			var optionA =  document.getElementById('txtOptionA').value;
	        			var optionB =  document.getElementById('txtOptionB').value;
	        			var optionC =  document.getElementById('txtOptionC').value;
	        			var optionD =  document.getElementById('txtOptionD').value;
	        			var correctAnswer = document.getElementById(checkedOption.value).value;
	        			var e = document.getElementById("selLevel");
	        			var level = e.options[e.selectedIndex].text;
	        			addQuestion(level, question, optionA, optionB, optionC, optionD, correctAnswer);
        			}
        		}
        	}
        	
	    	function addQuestion(level, question, optionA, optionB, optionC, optionD, correctAnswer){
	    		xmlHttp=GetXmlHttpObject()
	    		var url="/cadet/admin/questionBank/AddQuestion";
	    		url=url+"?level=" + level + "&question=" + question + "&optionA=" + optionA + "&optionB=" + optionB + "&optionC=" + optionC + "&optionD=" + optionD + "&correctAnswer=" + correctAnswer;
	    		xmlHttp.onreadystatechange=stateChanged 
	    		xmlHttp.open("GET",url,true)
	    		xmlHttp.send(null)
	    	}

	    	function stateChanged(){
	    		if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){
	    			var status=xmlHttp.responseText;
	    			if(status == "Question Added"){
	    				document.getElementById("tdNotification").setAttribute("style","color: green;");
	    				//document.getElementById("btnSubmit").value = ((int)(document.getElementById("btnSubmit").value) + 1);
	    				count++;
	    				document.getElementById("tdNotification").innerHTML = "<b>" + count + "</b> - Question Added...";
	    			}
	    		} 
	    	}

	    	function GetXmlHttpObject(){
	    		var xmlHttp=null;
	    		try{
	    		xmlHttp=new XMLHttpRequest();
	    		}
	    		catch (e) {
	    		try {
	    		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    		}
	    		catch (e){
	    		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    		}
	    		}
	    		return xmlHttp;
	    	}
		</script>
		
		<script type="text/javascript">
        $("#question").addClass("active");
        $("#collapse2").addClass("in");
        </script>
    </body>
</html>