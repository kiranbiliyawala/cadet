$(document).ready(function(e) {
	$("#frmCreateTest").validate();

	bkLib.onDomLoaded(function() {

		new nicEditor({fullPanel : true,iconsPath : "../../img/nicEditorIcons.gif"}).panelInstance("taTestDesc");
	});
});