function transferRegisteration(){
	window.location="./pages/Register.jsp";
}

function submit_val(){
	
	var pass = document.getElementById("password");
	
	pass.value = hashmap(pass.value);

}

function hashmap(input){
	var shaobj = new jsSHA(input,"ASCII");
	var hash = shaobj.getHash("SHA-512", "HEX");
	return hash;
}
