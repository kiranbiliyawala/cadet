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
			alert("Registration Done Successfully.");
		},
		error:function(e){
			alert("Something Wrong!");
		}
		
	});
}	
	
$(document).ready(function(){
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
		
	});
});