$(document).ready(function(e) {

	$("#frmSaveTest").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		}
	});

	if(window.location.href.lastIndexOf("#editTest")===-1)
		window.location.href += "#editTest";
});