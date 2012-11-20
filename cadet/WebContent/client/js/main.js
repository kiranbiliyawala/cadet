$.post("DashboardServlet",
		{ requestType : "gettests" },
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


function start_test(testtype,testid){
  var popup =  window.open('Test/'+testtype+'/CreateTest?testid='+testid,'_blank','location=0,menubar=0,statusbar=0,titelebar=0,toolbar=0,scrollbars=1,fullscreen=1,height='+screen.height+'width='+screen.width,false);
  popup.resizeTo(screen.availWidth,screen.availHeight);
  check_closure(popup);
}

function check_closure(popup){
	
	 setTimeout(function(){if(popup.closed)
		 {
		 window.location.reload();
		 }else{
			 check_closure(popup);
		 }
	 },1000);
}

function submit_val(){
	
	var pass = document.getElementById("password");
	
	pass.value = hashmap(pass.value);

}

function submit_val_cp(){
	
	if(!check_valid()){
		return false;
	}
	var pass = document.getElementById("changepassword");
	pass.value = hashmap(pass.value);
pass = document.getElementById("confirmpassword");
	pass.value = hashmap(pass.value);

}

function pageRedirect(pagePath) {

	window.location.href = pagePath;
}

function check_valid(){
	return $("#passwordform").valid();
 }

function hashmap(input){
	var shaobj = new jsSHA(input,"ASCII");
	var hash = shaobj.getHash("SHA-512", "HEX");
	return hash;
}
