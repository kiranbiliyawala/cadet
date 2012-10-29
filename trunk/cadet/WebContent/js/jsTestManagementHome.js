$(document).ready(function(e) {

	Handlebars.registerHelper("editButton",function(options) {
		return "edit"+options.fn(this);
	});
	Handlebars.registerHelper("deleteButton",function(options) {
		return "delete"+options.fn(this);
	});

	$.post("TestManagement",
		{ requestType : "getAllTests" },
		function(data,textStatus,xhr) {

			if(data.result===true) {

				try {
					var src = $("#tmpltTests").html();
					var template = Handlebars.compile(src);
					var output = template(data);

					$("#tblTests tbody").append(output);
				} catch(e) { alert(e.status+"\n"+e.message); }
			}
			else if(data.result==="DatabaseError") {
				databaseErrorDisplay("../DatabaseError");
			}
			else if(data.result==="ServerException") {
				serverExceptionDisplay("../ServerException");
			}
		}
	);

	$(".btnEdit").live("click",function(event) {
		console.log($(this).attr("id"));
//		$.post("testPage.jsp",{ testID : $(this).attr("id") });
	});

	$(".btnDelete").live("click",function(event) {

		var cnfrm = confirm("Are you sure you want to delete the test ?");
		var button = $(this);

		if(cnfrm===true) {

			$.post("TestManagement",
					{
						requestType : "deleteTest",
						testID : $(this).attr("id")
					},
					function(data,textStatus,xhr) {

						try {
							if(data.result===true) {
								var row = button.parent().parent();
								row.remove();
							} else if(data.result==="DatabaseError") {
								databaseErrorDisplay("../DatabaseError");
							}
							else if(data.result==="ServerException") {
								serverExceptionDisplay("../ServerException");
							}
						} catch(e) { alert(e.status+"\n"+e.message); }
				});
		}
	});
});