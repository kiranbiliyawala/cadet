function registerForTest(objTd,testId){
	
	$.ajax({
		url:"../../Test/Register",
		data:{
			"testid":testId
		},
		type:"POST",
		success:function(data){
			
				$(objTd).hide();
				var alertDiv = "<div style=\"position:absolute; margin-top:0.15%;\" class=\"alert alert-success offset4 span4\">You have saved the details <strong>successfully !!!</strong></div>";
					setTimeout(function() {
						$("body").prepend(alertDiv);
						setTimeout(function() { $(".alert").alert("close"); },3000);
					},600);
		},
		error:function(e){
			alert("Something Wrong!" + e.message + "\n" + e.status);
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