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

	$(".txtTimeCat").live("change",function(e) {

		try {
			var totalDuration = 0;
			$(".txtTimeCat").each(function() { totalDuration+=parseInt($(this).val()); });
		
			$(".badge.badge-info").html("&nbsp;&nbsp;"+totalDuration+"&nbsp;&nbsp;");
			$("#testDuration").val(totalDuration);
		} catch(e) { bootbox.alert(e.message); }
	});

	$("#btnTestSettings").bind("click",function(e) {

		/* from database */

		$.post("TestManagement",
			{
				requestType : "getTestTimeSettings",
				testId : $("#testId").val()
			},
			function(data,textStatus,xhr) {
				try {
					if(data.result===true) {

						$("#txtTestDate").val(data.testDate);
						$("#txtTPStart").val(data.startTime);
						$("#txtTPEnd").val(data.endTime);
						$("#optInitDifficulty option").eq(data.initDiff-1).attr("selected","selected");
						if(data.negMark===0) {
							$("#optDecreaseMark").attr("disabled","disabled");
							$("#optNegMarkFlag option").eq(1).attr("selected","selected");
						}
						else {
							$("#optDecreaseMark").removeAttr("disabled");
							$("#optDecreaseMark option").eq((data.negMark/10)-1).attr("selected","selected");
							$("#optNegMarkFlag option").eq(0).attr("selected","selected");
						}

						$.post("TestManagement",
							{
								requestType : "getLevelMarks",
								testId : $("#testId").val()
							},function(data,textStatus,xhr) {
								try {
									if(data.result===true) {
										$.each(data.levelMarks,function(i,path) {
											$("#txtLevel"+path.levelId).val(path.marks);
										});

										$.post("TestManagement",
											{
												requestType : "getTestCandCat",
												testId : $("#testId").val()
											},function(data,textStatus,xhr) {

												var src = $("#tmpltCandCat").html();
												var template = Handlebars.compile(src);
												var output = template(data);

												$("#tblTestCandCat tbody").html(output);
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

						$("#divTestSettings").modal("show");
					}
					else if(data.result==="DatabaseError") {
						pageRedirect("../DatabaseError.html");
					}
					else if(data.result==="ServerException") {
						pageRedirect("../ServerException.html");
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); flag = false; }
		});
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

	var alertInitDiffSaveDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have saved the initial difficulty <strong>successfully !!!</strong></div>";
	$("#btnSaveInitDiff").live("click",function(e) {

		e.preventDefault();
		$.post("TestManagement",
			{
				requestType : "saveInitDiff",
				testId : $("#testId").val(),
				initDiff : $("#optInitDifficulty option:selected").val()
			},function(data,textStatus,xhr) {

				try {
					if(data.result===true) {

						setTimeout(function() {
							$("#divTestSettings").prepend(alertInitDiffSaveDiv);
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
	});

	$("#optNegMarkFlag").live("change",function(e) {

		if($("#optNegMarkFlag option:selected").val()==="No")
			$("#optDecreaseMark").attr("disabled","disabled");
		else
			$("#optDecreaseMark").removeAttr("disabled","disabled");
	});

	$(".carousel").carousel({interval : false});

	var alertMarkSysSaveDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have saved the Marking System <strong>successfully !!!</strong></div>";
	$("#btnSaveNegMark").live("click",function(e) {

		e.preventDefault();
		$.post("TestManagement",
			{
				requestType : "saveNegMark",
				testId : $("#testId").val(),
				negMarkFlag : $("#optNegMarkFlag option:selected").val(),
				decreaseMark : $("#optDecreaseMark option:selected").val()
			},function(data,textStatus,xhr) {

				try {
					if(data.result===true) {

						setTimeout(function() {
							$("#divTestSettings").prepend(alertMarkSysSaveDiv);
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
	});

	$("#frmLevelMark").validate({
		errorClass : "text-error",
		submitHandler : function() {

			var inputList = $(".levelMark");

			console.log(inputList);

			function inputListClass(name,value) {

				this.inputName;
				this.inputValue;

				this.inputName = name;
				this.inputValue = value;
			}

			var data = new Array();
			var i=0;
			$(inputList).each(function() {
				data[i++] = new inputListClass($(this).attr("id"),$(this).val());
			});

			var dataJSON = $.toJSON(data);

			console.log(dataJSON);

			$.post("TestManagement",
				{
					requestType : "saveLevelMark",
					testId : $("#testId").val(),
					levelMarkList : dataJSON
				},
				function(data,textStatus) {
					try {
						if(data.result===true) {

							setTimeout(function() {
								$("#divTestSettings").prepend(alertMarkSysDiv);
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

	var alertCandCatDeleteDiv = "<div style=\"position:absolute; margin-top:1%;\" class=\"alert alert-success span4\">You have removed the category <strong>successfully !!!</strong></div>";
	$(".btnDelete").live("click",function(e) {

		var button = $(this);

		bootbox.confirm("Are you sure you want to remove candidate category ?",function(confirmed) {

			if(confirmed===true) {

				try {
					$.post("TestManagement",
						{
							requestType : "removeTestCandidateCategory",
							testId : $("#testId").val(),
							candidateCategoryName : button.attr("id")
						},
						function(data,textStatus,xhr) {

							try {
								if(data.result===true) {
									var row = button.parent().parent();
									var tBody = row.parent();
									var siblingRows = row.siblings();

									setTimeout(function() {
										row.remove();
										if(siblingRows.length===0) {
											tBody.append("<tr><td><p class=\"text-warning\">No Candidate Category Added</p></td><td></td><td></td><td></td><td></td></tr>");
										}
										$("#divTestSettings").prepend(alertCandCatDeleteDiv);
										setTimeout(function() { $(".alert").alert("close"); },3000);
									},600);
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

	$("#btnAddUserCat").live("click",function(e) {
		// Add User Category to Tests
	});
});