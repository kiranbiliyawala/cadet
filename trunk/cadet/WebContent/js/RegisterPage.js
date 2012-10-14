var RecaptchaOptions = {
		    theme : 'clean'
		 };
  
  function validate() {
	  
	  var username = document.getElementsByName("Username").value;
	  var password = document.getElementsByName("Password");
	  var password2 = document.getElementsByName("Password2");
	  var name = document.getElementsByName("Name").value;
	  var contact = document.getElementsByName("Contact").value;
	  
	  var username_error = document.getElementById("username_error");
	  var password_error = document.getElementById("password_error");
	  var name_error = document.getElementById("name_error");
	  var contact_error = document.getElementById("contact_error");
	  
	  username_error.innerHTML="";
	  password_error.innerHTML="";
	  name_error.innerHTML="";
	  contact_error.innerHTML="";
	  
	  var atpos=username.indexOf("@");
	  var dotpos=username.lastIndexOf(".");
	  if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
	    {
	    username_error.innerHTML="Invalid Email";
	    return false;
	    }
	  
	  if(Ajax_username("isUserNameAvailable?username="+username, "username_error")==false){
		  return false;
	  }
	  
	  if(password.value!=password2.value){
		  password_error.innerHTML="Passwords Don't Match...";
		  return false;
	  }
	  
	  if(name==null||name==""){
		  name_error.innerHTML="Name Cannot be blank";
		  return false;
	  }
	  
	  if(contact==null||contact==""){
		  contact_error.innerHTML="Contact cannot be blank";
		  return false;
	  }
	  
	  var challenge=document.getElementById("recaptcha_challenge_field").value;
	  var response=document.getElementById("recaptcha_response_field").value;
	  var message = "recaptcha_challenge_field="+challenge+"&recaptcha_response_field="+response;
	if( Ajax("VerifyCaptcha?"+message,"captchaerror")==false){
		return false;
	}
	
	password.value = hashmap(password.value);
	password2.value = hashmap(password2.value);
	
}
  
  function hashmap(input){
		var shaobj = new jsSHA(input,"ASCII");
		return shaobj.get("SHA-512","HEX");
	}
  
  function Ajax(url,div)
  {
  var xmlhttp;
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp.open("GET",url,false);
  xmlhttp.send();
  if(xmlhttp.status==200){
	  if(xmlhttp.responseText.substring(0,4)=="true"){
		  return true;
	  }else{
		  document.getElementById(div).innerHTML="Invalid Captcha";
		  return false;
	  }
  }else{
	  document.getElementById(div).innerHTML="Invalid Captcha";
	  return false;
  }
  }
  
  function Ajax_username(url,div)
  {
  var xmlhttp;
  if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
    }
  else
    {// code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  xmlhttp.open("GET",url,false);
  xmlhttp.send();
  if(xmlhttp.status==200){
		  return true;
  }else{
	  document.getElementById(div).innerHTML="UserName not Available";
	  return false;
  }
  }