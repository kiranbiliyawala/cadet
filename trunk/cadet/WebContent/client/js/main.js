
function start_test(testtype,testid){
	window.open('Test/'+testtype+'/CreateTest?testid='+testid,'_blank','location=0,menubar=0,statusbar=0,titelebar=0,toolbar=0',false);
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

function check_valid(){
	return $("#passwordform").valid();
 }

function hashmap(input){
	var shaobj = new jsSHA(input,"ASCII");
	var hash = shaobj.getHash("SHA-512", "HEX");
	return hash;
}
