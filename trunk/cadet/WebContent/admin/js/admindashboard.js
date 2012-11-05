$(document).ready(function() {
	$.post("AdminDashboardController", {
	//requestType : "gettestdetail" 
	}, function(data, textStatus, xhr) {

		try {
			if (data.result === true) {
				var src = $("#gettests").html();
				var template = Handlebars.compile(src);
				var output = template(data);
				console.log("hi");
				$("#tblTests tbody").append(output);
			}
		} catch (e) {
			bootbox.alert(e.status + "\n" + e.message);
		}
	});
});