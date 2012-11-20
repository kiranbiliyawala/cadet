$(document).ready(function(e) {
	$("#frmCreateTest").validate({
		errorClass : "text-error",
		errorPlacement : function(error,element) {
			error.appendTo(element.parent());
		}
	});

	bkLib.onDomLoaded(function() {

		new nicEditor({fullPanel : true,iconsPath : "../../img/nicEditorIcons.gif"}).panelInstance("taTestDesc");
	});
});