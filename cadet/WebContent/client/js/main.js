
function submit_val(){
	
	var pass = document.getElementById("password");
	
	pass.value = hashmap(pass.value);

}

function hashmap(input){
	var shaobj = new jsSHA(input,"ASCII");
	var hash = shaobj.getHash("SHA-512", "HEX");
	return hash;
}

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