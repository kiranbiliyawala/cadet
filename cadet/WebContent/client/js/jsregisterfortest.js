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
			//alert("Registration Done Successfully.");
			var alertDiv = "<div style=\"position:absolute; margin-top:0.15%;\" class=\"alert alert-success offset4 span4\">You have saved the details <strong>successfully !!!</strong></div>";
			$(document).ready(function(e) {
				setTimeout(function() {
					$("body").prepend(alertDiv);
					setTimeout(function() { $(".alert").alert("close"); },3000);
				},600);
			});
		},
		error:function(e){
			alert("Something Wrong!");
		}
		
	});
}	
	
$(document).ready(function(){
	$.ajax({
		url:"newRegisterList",
		dataType:"json",
		type:"GET",
		success:function(data){
			//console.log(data);
			var src = $("#tmpltTests").html();
			var template = Handlebars.compile(src);
			var output = template(data);

			$("#tblTests tbody").html(output);
				
			
			if(data==="DatabaseError"){
				pageRedirect("../DataError.html");
			}
			else if(data==="ServerException"){
				pageRedirect("../ServerException.html");
			}
					},
		error:function(e){
			bootbox.alert(e.status+"\n"+e.message);
		}
		
	});
});