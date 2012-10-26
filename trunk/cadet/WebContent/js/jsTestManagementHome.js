$(document).ready(function(e) {

	$.post("TestManagement",
		{ requestType : "getAllTests" },
		function(data,textStatus,xhr) {

			console.log(textStatus);
			console.log(data);
			if(textStatus==="success") {
				var src = $("#tmpltTests").html();
				var template = Handlebars.compile(src);
				var output = template(data);
	
				$("#tblTests tbody").append(output);
			}
		}
	);
});