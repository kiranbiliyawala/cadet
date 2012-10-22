$(document).ready(function(e) {
	
	$.post("../../GetTests",function(data,textStatus,xhr) {

		var src = $("#tmpltTests").html();
		var template = Handlebars.compile(src);
		var output = template(data);
	
		$("#tblTests tbody").append(output);
	});
});