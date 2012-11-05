$(document).ready(function(e) {

	$("#frmSaveTest").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		}
	});

	var alertAddCategoryDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have added the category <strong>successfully !!!</strong></div>";
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
								$("#divAddCat").prepend(alertAddCategoryDiv);
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

 								if($("#frmSaveTest table tbody tr:first-child td:eq('1') p").html()==="No Category Available")
 									$("#frmSaveTest table tbody tr:first-child").remove();
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

	$("#btnDelCat").bind("click",function(e) {

		var checkList = $("#chkDelCatList:checked");

		function checkListClass(name,value) {

			this.checkboxName;
			this.checkboxValue;

			this.checkboxName = name;
			this.checkboxValue = value;
		}

		var data = new Array();
		var i=0;
		$(checkList).each(function() {
			data[i++] = new checkListClass($(this).attr("name"),$(this).val());
		});

		var dataJSON = $.toJSON(data);

		$.post("TestManagement",
			{
				requestType : "removeCategory",
				testId : $("#testId").val(),
				delCatList : dataJSON
			},
			function(data,textStatus,xhr) {

				try {
					if(data.result===true) {

						$(checkList).each(function() {

							var row = $(this).parent().parent();
							var tbody = row.parent();
							var siblingRows = row.siblings();

							$(this).parent().parent().remove();
							if(siblingRows.length===0) {
								tbody.append("<tr><td></td><td><p class=\"text-warning\">No Category Available</p></td><td></td><td></td></tr>");
							}
						});
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

	$("#btnTestSettings").bind("click",function(e) {

		$("#txtTestDate").val(new Date().getDate()+"."+new Date().getMonth()+"."+new Date().getFullYear());
		$("#txtTPStart,#txtTPEnd").val(new Date().getHours()+":"+new Date().getMinutes());
		$("#divTestSettings").modal("show");
	});

	$("#txtDP").datepicker();
	$("#txtTPStart,#txtTPEnd").timepicker({
		minuteStep : 1,
		showMeridian : false,
		disableFocus : true,
		modalBackdrop : true
	});

	var alertTimeSaveDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have saved the details <strong>successfully !!!</strong></div>";
	$("#frmDateTime").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		},
		submitHandler : function() {
			$.post("TestManagement",
				{
					requestType : "saveTimeSettings",
					testId : $("#testId").val(),
					testDate : $("#txtTestDate").val(),
					startTime : $("#txtTPStart").val(),
					endTime : $("#txtTPEnd").val(),
				},
				function(data,textStatus,xhr) {
					try {
						if(data.result===true) {

							setTimeout(function() {
								$("#divTestSettings").prepend(alertTimeSaveDiv);
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
	});
});




















