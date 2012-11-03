
$.post("DashboardServlet",
		{ requestType : "getTests" },
		function(data,textStatus,xhr) {

			try {
				if(data.result===true) {
					var src = $("#gettests").html();
					var template = Handlebars.compile(src);
					var output = template(data);
	
					$("#tblTests tbody").append(output);
				}
			} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});