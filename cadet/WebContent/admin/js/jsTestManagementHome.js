$(document).ready(function(e) {

	Handlebars.registerHelper("deleteButton",function(options) {
		return "delete"+options.fn(this);
	});

	$.post("TestManagement",
		{ requestType : "getAllTests" },
		function(data,textStatus,xhr) {

			try {
				if(data.result===true) {

					var src = $("#tmpltTests").html();
					var template = Handlebars.compile(src);
					var output = template(data);
	
					$("#tblTests tbody").append(output);
				}
				else if(data.result==="DatabaseError") {
					pageRedirect("../DatabaseError.html");
				}
				else if(data.result==="ServerException") {
					pageRedirect("../ServerException.html");
				}
			} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});

	$(".btnEdit").live("click",function(event) {

		try {
			$.get("TestManagement",
				{
					requestType : "editTest",
					testId : $(this).attr("id")
			});
		}catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});

	var alertDiv = "<div style=\"position:absolute; margin-top:0.1%;\" class=\"alert alert-success offset5 span3\">You have deleted the test <strong>successfully !!!</strong></div>";
	$(".btnDelete").live("click",function(event) {

		var button = $(this);

		event.preventDefault();
		bootbox.confirm("Are you sure you want to delete the test ?",function(confirmed) {

			if(confirmed===true) {

				try {
					$.post("TestManagement",
							{
								requestType : "deleteTest",
								testId : button.attr("id")
							},
							function(data,textStatus,xhr) {

								try {
									if(data.result===true) {
										var row = button.parent().parent();
										var tBody = row.parent();
										var siblingRows = row.siblings();

										setTimeout(function() {
											row.remove();
											$("body").prepend(alertDiv);
											setTimeout(function() { $(".alert").alert("close"); },3000);
										},600);

										if(siblingRows.length===0) {
											tBody.append("<tr><td><p class=\"text-warning\">No Test Available</p></td><td></td><td></td><td></td><td></td></tr>");
										}
									} else if(data.result==="DatabaseError") {
										pageRedirect("../DatabaseError.html");
									}
									else if(data.result==="ServerException") {
										pageRedirect("../ServerException.html");
									}
								} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
						});
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
			}
		});
	});
});