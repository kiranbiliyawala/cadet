$(document).ready(function(){
	$.ajax({
		url:"ViewAll",
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