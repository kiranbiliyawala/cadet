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
        <title>Question Bank | Add/View Question</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="../../css/bootstrap.css">
        <style>
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            div#deleteConfirmationPopup
			{
			    position:absolute;
			    display:none;
			    top:235px;
			    left:50%;
			    width:400px; 
			    margin-left:-250px;
			    border:5px solid black;
			    -webkit-box-shadow:rgba(110,110,110,.6) 10px 10px 10px;
			    border-radius:10px; 
			    padding:20px;
			    background-color:rgba(250,250,250,.8);
			}
			div#deleteConfirmationPopupTitle{
				font-weight:600;
				position:absolute;
			    display:none;
			    top:200px;
			    left:50%;
			    width:420px;
			    height:25px;
			    margin-left:-250px;
			    border:5px solid black;
			    -webkit-box-shadow:rgba(110,110,110,.6) 10px 10px 10px;
			    border-radius:10px;
			    padding-left:20px;
			    padding-top:5px; 
			    background-color:rgba(250,250,250,.8);
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

		<% 
		session.setAttribute("categoryId", "1");
		session.setAttribute("categoryName", "Quantitative");
		%>
		<form id="form1">
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
                                    <li><a href="ViewQuestion">Add/View Questions</a></li>
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
                            	<table>
                           			<tr>
                           				<td><a class="brand" href="#">Select Category:</a></td>
                           				<td valign="bottom" style="padding-top: 10px">
                           					
                           					<select id="selCategory" onChange="viewQuestion()"> 
                           						<option id="0">All Categories</option>
							                	<c:if test="${requestScope.status eq 'success'}">
							                		<c:forEach items="${requestScope.categories}" var="category">
							                			<option id="${category.categoryId}">${category.categoryName}</option>
								                    </c:forEach>
								                </c:if>
		                        			</select>
                           				</td>
                           			</tr>
                            	</table>
                            </div>
                            <div class="container pull-right"><button class="btn btn-primary" id="btnAddNewQuestion" name="btnAddNewQuestion" onClick="addQuestion()">Add New Question</button></div>
                        </div>
                    </div>
                    <% int cnt=1; %>
                   	
	                    <div>
	                    	<input type="submit" id="btnSubmit" name="btnSubmit" style="display:none;" value=""/>
	                		<table>
			                    <tr>
			                        <td colspan="3">

										<c:if test="${requestScope.status eq 'success'}">
											<input type="hidden" id="hdnCategoryId" name="hdnCategoryId" value="${requestScope.categoryId}"/>
											<div class="accordion" id="accordionQuestion">
					                			<c:forEach items="${requestScope.questions}" var="question">
													<div class="accordion-group">
														<div class="accordion-heading span9">
															<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionQuestion" href="#collapseQuestion${question.questionId}">
																<span class="span7"><b>Question <%= cnt++%></b></span>
																<a href="#" id="lnkRemove${question.questionId}" name="lnkRemove${question.questionId}" class="span1 pull-right" onClick="document.getElementById('btnSubmit').value = '${question.questionId}'; document.getElementById('deleteConfirmationPopup').style.display = 'block'; document.getElementById('deleteConfirmationPopupTitle').style.display = 'block'; return false;">Remove</a>
																<a href="#" id="lnkEdit${question.questionId}" name="lnkEdit${question.questionId}" class="span1 pull-right" onClick="modifyQuestion('Edit','${question.questionId}')">Edit</a>
															</a>
												    	</div>
														<div id="collapseQuestion${question.questionId}" class="accordion-body collapse">
															<div class="accordion-inner span9">
																<table style="width:90%">
																	<tr>
																		<td><textarea id="txtQuestion${question.questionId}" name="txtQuestion${question.questionId}" style="width:232%; border-radius:10px;" rows="4">${question.question}</textarea></td>
																	</tr>
																	<tr><td>&nbsp;</td></tr>
																	<tr>
																		<td>&nbsp;&nbsp;<b>A.</b> ${question.optionA}</td>
																		<td rowspan="2" align="right"><b>Level:</b> ${question.levelId}</td>
																	</tr>
																	<tr>
																		<td>&nbsp;&nbsp;<b>B.</b> ${question.optionB}</td>
																	</tr>
																	<tr>
																		<td>&nbsp;&nbsp;<b>C.</b> ${question.optionC}</td>
																		<td rowspan="2" align="right"><b>Correct Answer:</b> ${question.correctAnswer}</td>
																	</tr>
																	<tr>
																		<td>&nbsp;&nbsp;<b>D.</b> ${question.optionD}</td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
							                    </c:forEach>
											</div>
						                </c:if>

											<!-- 
	                                    <div id="divQuestionHeader"></div>
										<c:if test="${requestScope.status eq 'success'}">
											<input type="hidden" id="hdnCategoryId" name="hdnCategoryId" value="${requestScope.categoryId}"/>
					                		<c:forEach items="${requestScope.questions}" var="question">
					                			<div id="divQuestionTitle${question.questionId}">
													<b>Question %= cnt++%></b><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
													<a href="#" id="lnkEdit${question.questionId}" name="lnkEdit${question.questionId}" onClick="modifyQuestion('Edit','${question.questionId}')">Edit</a> | 
													<a href="#" id="lnkRemove${question.questionId}" name="lnkRemove${question.questionId}" onClick="document.getElementById('btnSubmit').value = '${question.questionId}'; document.getElementById('deleteConfirmationPopup').style.display = 'block'; document.getElementById('deleteConfirmationPopupTitle').style.display = 'block'; return false;">Remove</a>
					                			</div>
						                		<div id="divQuestionBody${question.questionId}">
						                			<textarea id="txtQuestion${question.questionId}" name="txtQuestion${question.questionId}" style="width:450%; border-radius:10px;" rows="4">${question.question}&#10;- Level ${question.levelId}</textarea><br/><br/>
		                                            <input type="radio" id="radOptionA${question.questionId}" name="radOption${question.questionId}" value="${question.questionId}" disabled='disabled' style="vertical-align: top; padding-left: 10px;"/>&nbsp;&nbsp;${question.optionA}<br/>
		                                            <input type="radio" id="radOptionB${question.questionId}" name="radOption${question.questionId}" value="${question.questionId}" disabled='disabled' style="vertical-align: top; "/>&nbsp;&nbsp;${question.optionB}<br/>
		                                            <input type="radio" id="radOptionC${question.questionId}" name="radOption${question.questionId}" value="${question.questionId}" disabled='disabled' style="vertical-align: top; "/>&nbsp;&nbsp;${question.optionC}<br/>
		                                            <input type="radio" id="radOptionD${question.questionId}" name="radOption${question.questionId}" value="${question.questionId}" disabled='disabled' style="vertical-align: top; "/>&nbsp;&nbsp;${question.optionD}<br/>
							                    </div>
							                    <hr width="480%">
						                    </c:forEach>
						                </c:if>
						                 -->
			                        </td>
			                    </tr>
			                </table>
	    				</div>
	    				<div id="deleteConfirmationPopupTitle">
	                		Delete Question
	                	</div>
		                <div id="deleteConfirmationPopup">
		                    <p>Are you sure you want to delete the question permanently?</p>
						    <p>
						    <center>
						        <a href="#" class="btn btn-primary" id="lnkRemoveQuestion" name="lnkRemoveQuestion" onClick="modifyQuestion('Remove','btnSubmit')">
						            Yes
						        </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						        <a class="btn btn-danger" onclick="document.getElementById('deleteConfirmationPopup').style.display = 'none'; document.getElementById('deleteConfirmationPopupTitle').style.display = 'none'; return false;">
						            No
						        </a>
						    </center>
						    </p>
						</div>
                </div>

            </div>	<!--/.row div -->

        </div>	<!--/.container div -->
		</form>

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
        
    		window.addEventListener('load', doFirst, false);
    		
    		function doFirst(){
        		var category = document.getElementById("hdnCategoryId").value;
       			var categoryOptions = document.getElementById("selCategory");
       		    for(var i = 0; i < categoryOptions.length; i++){
       		        if(categoryOptions[i].id == category){
       		        	categoryOptions[i].selected = true;
       		        }
       		    }
    		}
        
        	function viewQuestion(){
    			var e = document.getElementById("selCategory");
    			var categoryId = e.options[e.selectedIndex].id;
    			window.location = "/cadet/admin/questionBank/ViewQuestion?categoryId=" + categoryId;
        	}

        	function addQuestion(){
        		
    			var e = document.getElementById("selCategory");
    			var categoryId = e.options[e.selectedIndex].id;
    			var categoryName = e.options[e.selectedIndex].text;
    			alert(categoryId + "name" + categoryName);
       	        document.forms[0].action = "/cadet/admin/questionBank/AddQuestions?i=" + categoryId + "&c=" + categoryName;
       	     	document.getElementById("btnSubmit").click();
    			//window.location = "/cadet/admin/questionBank/AddQuestions?i=" + categoryId + "&c=" + categoryName;
        	}

        	function modifyQuestion(action,val){
        		if(action=='Remove'){
        			queId = document.getElementById("btnSubmit").value;
        			removeQuestion(queId);
        		}
        		else if (action=='Edit') {
					editQuestionId = val;
					alert("action=" + action + " and question id=" + val);
	       	        document.forms[0].action = "/cadet/admin/questionBank/EditQuestion?questionId=" + val;
	       	     	document.getElementById("btnSubmit").click();
				}
        	}

        	function removeQuestion(questionId){
        		xmlHttp1=GetXmlHttpObject1()
        		var url="RemoveQuestion";
        		url=url+"?questionId=" + questionId;
        		removeQuestionId = questionId;
        		xmlHttp1.onreadystatechange=stateChanged1 
        		xmlHttp1.open("GET",url,true)
        		xmlHttp1.send(null)
        		}

        	function stateChanged1(){
        		if(xmlHttp1.readyState==4 || xmlHttp1.readyState=="complete"){
        			var status=xmlHttp1.responseText;
        			if(status == "Question Removed"){
        				document.getElementById('deleteConfirmationPopup').setAttribute("style","display: none;");
        				document.getElementById('deleteConfirmationPopupTitle').setAttribute("style","display: none;");
        				document.getElementById("divQuestionTitle" + removeQuestionId).setAttribute("style","display: none;");
        				document.getElementById("divQuestionBody" + removeQuestionId).setAttribute("style","display: none;");
        			}
        		} 
        	}

        	function GetXmlHttpObject1(){
        		var xmlHttp1=null;
        		try{
        		xmlHttp1=new XMLHttpRequest();
        		}
        		catch (e) {
        		try {
        		xmlHttp1=new ActiveXObject("Msxml2.XMLHTTP");
        		}
        		catch (e){
        		xmlHttp1=new ActiveXObject("Microsoft.XMLHTTP");
        		}
        		}
        		return xmlHttp1;
        	}
        </script>
    </body>
</html>