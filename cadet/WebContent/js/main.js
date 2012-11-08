function transferRegisteration(){
	window.location="Register";
}

function submit_val(){
	
	if(!validate()){
		return false;
	}
	
	var pass = document.getElementById("password");
	pass.value = hashmap(pass.value);
	return true;
}

function submit_email(){
	if(!validate2()){
		return false;	
	}
	return true;
}



function hashmap(input){
	var shaobj = new jsSHA(input,"ASCII");
	var hash = shaobj.getHash("SHA-512", "HEX");
	return hash;
}
