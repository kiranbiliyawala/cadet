<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
        <script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="../../js/jquery-1.8.2.js"></script>
        <script src="../../js/handlebars.js"></script>
</head>
<body>





<button class="btn btn-info" id="btnGetTest">Get Test to Register </button>

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
<script>
function registerForTest(objTd,testId){
	
	$.ajax({
		url:"../../Test/Register",
		dataType:"json",
		data:{
			"testid":testId
		},
		type:"POST",
		success:function(data){
			//console.log(data);
			
			$(objTd).hide();
			alert("Registration Done Successfully.")
		},
		error:function(e){
			alert("Something Wrong!");
		}
		
	})
	
}	
	
$("#btnGetTest").click(function(){
	$.ajax({
		url:"../../Test/newRegisterList",
		dataType:"json",
		type:"GET",
		success:function(data){
			//console.log(data);
			var src = $("#tmpltTests").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblTests tbody").html(output);
		},
		error:function(e){
			alert("Something Wrong");
		}
		
	})
	
})
</script>
</body>
</html>