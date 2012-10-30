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
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
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

		try {
			$.post("testPage.jsp",{ testID : $(this).attr("id") });
		}catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});

	$(".btnDelete").live("click",function(event) {

		var button = $(this);

		event.preventDefault();
		bootbox.confirm("Are you sure you want to delete the test ?",function(confirmed) {

			if(confirmed===true) {

				try {
					$.post("TestManagement",
							{
								requestType : "deleteTest",
								testID : button.attr("id")
							},
							function(data,textStatus,xhr) {

								try {
									if(data.result===true) {
										var row = button.parent().parent();
										var tBody = row.parent();
										var siblingRows = row.siblings();
										row.remove();

										if(siblingRows.length===0) {
											tBody.append("<tr><td><p class=\"text-warning\">No Test Available</p></td><td></td><td></td><td></td><td></td></tr>");
										}
									} else if(data.result==="DatabaseError") {
										databaseErrorDisplay("../DatabaseError");
									}
									else if(data.result==="ServerException") {
										serverExceptionDisplay("../ServerException");
									}
								} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
						});
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
			}
		});
	});
});