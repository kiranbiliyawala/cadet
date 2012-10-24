$(document).ready(function(e) {

	var data = [];

    var src = $("#tmpltTests").html();
	console.log(src);
	var template = Handlebars.compile(src);
	console.log(template);
	var output = template(data);
	console.log(output);

	$("#tblTests tbody").append(output);
});