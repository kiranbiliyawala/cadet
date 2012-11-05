$(document).ready(function(e) {

	$("#frmSaveTest").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		}
	});

	var alertDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have added the category <strong>successfully !!!</strong></div>";
	$("#frmNewCat").validate({
		errorClass : "text-error",
		submitHandler : function() {
			$.post("TestManagement",
				{
					requestType : "newCategory",
					categoryName : $("#txtCategoryName").val()
				},
				function(data,textStatus) {
					try {
						if(data.result===true) {

							setTimeout(function() {
								$("#divAddCat").prepend(alertDiv);
								$("#txtCategoryName").val("");
								setTimeout(function() { $(".alert").alert("close"); },3000);
							},600);
							$("#divNewCat").modal("hide");
							$("#btnAddCat").click();
						} else if(data.result==="DatabaseError") {
							pageRedirect("../DatabaseError.html");
						}
						else if(data.result==="ServerException") {
							pageRedirect("../ServerException.html");
						}
					} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
			});
		}
	});

	$("#btnAddCatConfirm").live("click",function(e) {

		try {
			var option = $("#optCatList option:selected").val();

			if(option==="optNewCat") {

				$("#divAddCat").modal("hide");
				$("#divNewCat").modal("show");

			} else {

				$.post("TestManagement",
					{
						requestType : "addCategory",
						testId : $("#testId").val(),
						categoryId : option
 					},
					function(data,textStatus,xhr) {
 						try {
 							if(data.result===true) {

 								console.log(data.category);
 								var src = $("#tmpltCategoryDetails").html();
 								var template = Handlebars.compile(src);
 								var output = template(data);

 								$("#frmSaveTest tbody").append(output);

 								$("#divAddCat").modal("hide");
 								setTimeout(function() {
 									$("#body").prepend(alertDiv);
 									setTimeout(function() { $(".alert").alert("close"); },3000);
 								},600);
 							}
 							else if(data.result==="DatabaseError") {
 								pageRedirect("../DatabaseError.html");
 							}
 							else if(data.result==="ServerException") {
 								pageRedirect("../ServerException.html");
 							}
 						} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
				});
			}
		} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});

	$("#btnAddCat").live("click",function(e) {

		$.post("TestManagement",
			{ requestType : "getAllCategories" },
			function(data,textStatus,xhr) {
				try {
					if(data.result===true) {

						var src = $("#tmpltCategories").html();
						var template = Handlebars.compile(src);
						var output = template(data);

						var initOption = "<option value=\"optNewCat\">--(New Category)--</option>";
						$("#optCatList").html(initOption+output);

						$("#divAddCat").modal("show");
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