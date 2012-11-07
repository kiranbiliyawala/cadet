$.post("ViewTestResult",
		{ requestType : "getresults" },
		function(data,textStatus,xhr) {

			try {
				if(data.result===true) {
					var src = $("#getresults").html();
					var template = Handlebars.compile(src);
					var output = template(data);

					$("#tblResults tbody").append(output);
				}
			} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});

