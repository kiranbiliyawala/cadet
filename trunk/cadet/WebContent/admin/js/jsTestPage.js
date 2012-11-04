$(document).ready(function(e) {

	$("#frmSaveTest").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		}
	});

	$("#btnAddCat").bind("click",function(e) {

		$.post("TestManagement",
			{ requestType : "getAllCategories" },
			function(data,textStatus,xhr) {
				try {
					if(data.result===true) {

						var src = $("#tmpltCategories").html();
						var template = Handlebars.compile(src);
						var output = template(data);

						$("#optCatList").append(output);
					}
					else if(data.result==="DatabaseError") {
						pageRedirect("../DatabaseError.html");
					}
					else if(data.result==="ServerException") {
						pageRedirect("../ServerException.html");
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
		});
	});
});