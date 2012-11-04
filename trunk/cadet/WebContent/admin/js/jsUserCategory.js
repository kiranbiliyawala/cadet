$(document).ready(function(e) {
$.post("GetAllUserCategories",
		{ requestType : "getUserCategory" },
		function(data,textStatus,xhr) {
			try {
				if(data.result===true) {
					
					var src = $("#getusercategory").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					console.log(output); 
					$("#tblusercategories tbody").append(output);
				}
			} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});
});