<%@page import="org.cadet.admin.model.CategoryManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
        </style>
        <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
        <link rel="stylesheet" href="../../css/main.css">

        <script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
	</head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
		<form id="form1">
 <jsp:include page="/admin/NavBar.jsp"></jsp:include>
        	<!--/.navbar -->
        
        <div class="container-fluid">
        	<div class="row">
                
<jsp:include page="/admin/Accordian.jsp"></jsp:include>
	<!--/#accordion -->

				<div class="container-fluid span9 offset*">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container-fluid pull-left">
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
                            <div class="container-fluid pull-right"><button class="btn btn-primary" id="btnAddNewQuestion" name="btnAddNewQuestion" onClick="addQuestion()">Add New Question</button></div>
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
											<input type="hidden" id="hdnQuestionId" name="hdnQuestionId" value=""/>
											<div class="accordion" id="accordionQuestion">
					                			<c:forEach items="${requestScope.questions}" var="question">
													<div id="div${question.questionId}" class="accordion-group">
														<div class="accordion-heading span9">
															<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionQuestion" href="#collapseQuestion${question.questionId}">
																<c:set var="questionBegining" value="${fn:substring(question.question, 0, 20)}" />
																<table>
																	<tr>
																		<td class="span7" style="font-weight: 600;">Question: ${questionBegining}...</td>
																		<td><a href="#" id="lnkEdit${question.questionId}" name="lnkEdit${question.questionId}" class="btn btn-info" onClick="modifyQuestion('Edit','${question.questionId}')">Edit</a></td>
																		<td style="padding-left: 20px;"><a href="#deleteConfirmationPopup" class="btn btn-danger" id="lnkRemove${question.questionId}" name="lnkRemove${question.questionId}" data-toggle="modal" onClick="document.getElementById('btnSubmit').value = '${question.questionId}'; return false;">Remove</a></td>
																	</tr>
																</table>
															</a>
												    	</div>
														<div id="collapseQuestion${question.questionId}" class="accordion-body collapse">
															<div class="accordion-inner span9">
																<table style="width:100%">
																	<tr>
																		<td style="" colspan="2" align="justify">
																			<label id="txtQuestion${question.questionId}" name="txtQuestion${question.questionId}" style="background-color:white; border:none; rows="4" readonly="readonly">${question.question}</label>
																		</td>
																	</tr>
																	<tr><td colspan="2">&nbsp;</td></tr>
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
			                        </td>
			                    </tr>
			                </table>
	    				</div>
	    				
		                <div id="deleteConfirmationPopup" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		                	<div class="modal-header">
							    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							    <h3 id="myModalLabel">Delete Question</h3>
							</div>
							<div class="modal-body">
							    <p>Are you sure you want to delete the category permanently?</p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-primary" id="btnRemoveQuestion" name="btnRemoveQuestion" value="Yes" onClick="modifyQuestion('Remove','btnSubmit')" data-dismiss="modal" aria-hidden="true"/>
							    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">No</button>
							</div>
						</div>
                </div>

            </div>	<!--/.row div -->

        </div>	<!--/.container div -->
		</form>

		
        <jsp:include page="/admin/Footer.jsp"></jsp:include>

        <script src="../../js/jquery-1.8.2.js"></script>
        <script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

        <script src="../../js/bootstrap.js"></script>

        
        <script src="../../js/main.js"></script>
        <script type="text/javascript">
        
    		window.addEventListener('load', doFirst, false);
    		
    		function doFirst(){
        		var category = document.getElementById("hdnCategoryId").value;
        		if(category == "0")
        			document.getElementById("btnAddNewQuestion").setAttribute('disabled','disabled');
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
    			window.location = "/cadet/admin/questionBank/ViewQuestion?hdnCategoryId=" + categoryId;
        	}

        	function addQuestion(){
        		
    			var e = document.getElementById("selCategory");
    			var categoryId = e.options[e.selectedIndex].id;
    			var categoryName = e.options[e.selectedIndex].text;
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
					document.getElementById("hdnQuestionId").value = val;
	       	        document.forms[0].action = "/cadet/admin/questionBank/EditQuestions";
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
        				document.getElementById("div" + removeQuestionId).setAttribute("style","display: none;");
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
        
         <script type="text/javascript">
        $("#question").addClass("active");
        $("#collapse2").addClass("in");
        </script>
        
    </body>
</html>