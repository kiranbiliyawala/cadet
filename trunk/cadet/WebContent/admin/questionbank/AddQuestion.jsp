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
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">CADET</a>
                    <div class="nav-collapse collapse">
                       <ul class="nav">
                           <li class="active"><a href="#home">Home</a></li>
                           <li><a href="#questionBank">Question Bank</a></li>
                           <li><a href="#testManagement">Test Management</a></li>
                           <li><a href="#candidateCategory">Candidate Category</a></li>
                       </ul>
                    </div>
                </div>	<!--/.container -->
            </div>
        </div>	<!--/.navbar -->
        
        <div class="container">
        	<div class="row">
                <div class="accordion span2" id="accordionMenu">

                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-parent="#accordionMenu" href="#">Home</a>
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
                                    <li><a href="ViewCategory">Add/View Category</a></li>
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
                                    <li><a href="#">Create/View Test</a></li>
                                    <li><a href="#">Result</a></li>
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
                    <div class="navbar navbar-inverse">
                        <div class="navbar-inner">
                            <div class="container pull-left">
                            	<a class="brand" href="#">Add Question - Category <font style="text-transform:capitalize;"><%= session.getAttribute("categoryName") %></font></a>
                            </div>
                        </div>
                    </div>
                    <div>
                    	<% int count = 0;%>
                    	<form action="" method="POST"> 
                    	<input type="submit" id="btnSubmit" name="btnSubmit" style="display:none;" class=""/>
                    	<table>
                    		<tr>
                    			<td id="tdNotification" style="display: none; color:green;">
									<%=count %> - Question Added...			                    				
                    			</td>
                    		</tr>
		                    <tr>
		                        <td colspan="3" width="100%">
		                        	Question:<br/>
		                        	<textarea id="txtQuestion" name="txtQuestion" style="width:800px" rows="4"></textarea>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td width="70%">
		                        	A.
		                        	<input type="radio" id="radOptionA" name="radOption" value="txtOptionA" style="vertical-align:baseline;"/>
		                        	<input type="text" id="txtOptionA" name="txtOptionA"/>		                        </td>
		                        <td align="right" rowspan="4">Set Level:</td>
		                        <td align="left" rowspan="4">
		                            <select id="selOption">
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
		                    </tr>
                    		<tr>
		                        <td>
		                            B.
		                            <input type="radio" id="radOptionB" name="radOption" value="txtOptionB" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionB" name="txtOptionB" value=""/>		                        </td>
	                        </tr>
	                        <tr>
		                        <td>
		                            C.
		                            <input type="radio" id="radOptionC" name="radOption" value="txtOptionC" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionC" name="txtOptionC" value=""/>
		                        </td>
	                        </tr>
	                        <tr>
		                        <td>
		                            D.
		                            <input type="radio" id="radOptionD" name="radOption" value="txtOptionD" style="vertical-align:baseline;"/>
									<input type="text" id="txtOptionD" name="txtOptionD" value=""/>
		                        </td>
		                    </tr>
		                    <tr style="height:100px">
		                        <td colspan="3" align="center">
		                        	<input type="button" class="btn btn-primary" id="btnSaveQuestion" name="btnSaveQuestion" onClick="changeAction('SaveQuestion', <%= count %>)" value="Save Question" />
		                        	&nbsp;&nbsp;&nbsp;
		                        	<button class="btn btn-danger" id="btnBack" name="btnBack" onClick="back('<%=session.getAttribute("categoryId")%>')">Back</button>
		                        </td>
		                    </tr>
		                </table>
		                </form>
					</div>
                </div>

            </div>	<!--/.row div -->

        </div>	<!--/.container div -->

		<hr>
        <footer>
                <div class="container row">
                    <a class="span pull-right" href="#">About</a>
                    <a class="span pull-right" href="#">FAQ</a>
                    <a class="span pull-right" href="#">Contact us</a>
                    <a class="span pull-right" href="#">Help</a>
                    <a class="span pull-right" href="#">Privacy & Policy</a>
                </div>
        </footer>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

        <script src="../../js/bootstrap.js"></script>

        <script src="../../js/plugins.js"></script>
        <script src="../../js/main.js"></script>
        
        <script type="text/javascript">
        
        	//window.addEventListener('load', doFirst, false);
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
	    	
	    	function validate( val ){
	    		error = "";
	    		if(val == 'SaveQuestion'){
	    			var elementOption = document.getElementById("selOption");
        			var level = elementOption.options[elementOption.selectedIndex].text;
        			if(level == 'Select Level')
        				error += "Select level for question\n";
	    		}
	    		return error;
	    	}

	    	function changeAction( action, cnt ){
        		if(action == 'SaveQuestion'){
        			validate(action);
        			if(error.length > 0)
        				alert(error);
        			var checkedOption = getCheckedRadio(document.forms[0].elements.radOption);
        			var question = document.getElementById('txtQuestion').value;
        			var optionA =  document.getElementById('txtOptionA').value;
        			var optionB =  document.getElementById('txtOptionB').value;
        			var optionC =  document.getElementById('txtOptionC').value;
        			var optionD =  document.getElementById('txtOptionD').value;
        			var correctAnswer = document.getElementById(checkedOption.value).value;
        			var e = document.getElementById("selOption");
        			var level = e.options[e.selectedIndex].text;
        			addQuestion(level, question, optionA, optionB, optionC, optionD, correctAnswer);
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
	    	
        	function back( categoryId ){        		
       	        document.forms[0].action = "/cadet/admin/questionBank/ViewQuestion?categoryId=" + categoryId;
       	     	document.getElementById("btnSubmit").click();
        	}
        </script>
    </body>
</html>