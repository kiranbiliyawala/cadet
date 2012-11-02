 var category;
var qno;
var Question;
var optiona;
var optionb;
var optionc;
var optiond;
var answer;
var QID;
var selectedop = null;
var flag = false;
var lock = false;


function update_Attempted(command,cat,question){
	

	if(command=="Attempted"){
		$("#Qstub"+cat+question).addClass("Attempted");
		$("#Qstub"+cat+question).removeClass("NotAttempted");
		$("#Qstub"+cat+question).removeClass("Flagged");
	}
	else if(command=="Flagged"){
		$("#Qstub"+cat+question).addClass("Flagged");
		$("#Qstub"+cat+question).removeClass("NotAttempted");
		$("#Qstub"+cat+question).removeClass("Attempted");
	}
	else if(command=="NotAttempted"){
		$("#Qstub"+cat+question).addClass("NotAttempted");
		$("#Qstub"+cat+question).removeClass("Attempted");
		$("#Qstub"+cat+question).removeClass("Flagged");
	}
}

function updatecatview(cat){
	for(a in categories){
		$("#dispcat"+a).hide(0);
	}
	$("#dispcat"+cat).show(2);
}

function update_data(){
	$("#QNO").html(qno);
	$("#Question").html(Question);
}

function ajax_caller(callurl,data_value,async_value,callback){
	$.ajax({
		url:callurl,
		data:data_value,
		async:async_value,
		succsess:callback(result)
	}
		);
}


function show_cat(covercat){
	$(".CatCover").hide();
	$("#"+covercat).show();
}

function change_question_data(){
	$("#option1").html(optiona);
	$("#option2").html(optionb);
	$("#option3").html(optionc);
	$("#option4").html(optiond);
	$("#Question").html(Question);
	$("QNO").html(qno);
	
	if(answer==A){
	$("#option1").attr("checked",true);	
	}
	if(answer==B){
	$("#option2").attr("checked",true);	
	}
	if(answer==C){
	$("#option3").attr("checked",true);	
	}
	if(answer==D){
	$("#option4").attr("checked",true);	
	}
}

function any(){}

function submit_next(){

	var ans = $("input[name=Answer]:checked","#Options").val();
	if(ans!=null)
	{
		submit_answer(ans);
	}else if(attempted=="Attempted")
	{
		remove_answer();
	}
	
	if(flag=true){
		remove_answer();
	}
		
	var maxamount = questiondistribution.category.NoOfQuestions;
	if(qno<maxamount){
		qno = qno+1;
	}else{
		show_catselector();
	}
}

function skip(){
	var maxamount = questiondistribution.category.NoOfQuestions;
	if(qno<maxamount){
		qno = qno+1;
	}else{
		show_catselector();
	}
}

function previous(){
	if(qno>1){
		qno = qno-1;
	}else{
		show_catselector();
	}
}

function show_catselector(){
	$("#question_portion").hide();
	$("#Section").hide();
	$("#Controls").hide();
	$("#catselector").show();
}

function hide_catselector(){
	$("#question_portion").show();
	$("#Section").show();
	$("#Controls").show();
	$("#catselector").hide();
}


function clear_answer(){		
		$("#option1").attr("checked",false);
		$("#option2").attr("checked",false);
		$("#option3").attr("checked",false);
		$("#option4").attr("checked",false);
}

function change_que(cat,queno){
refresh_que(cat,queno)

}

function refresh_que(cat,queno)
{
var data={CAT:cat,QNO:queno};

category=cat;
qno=queno;
ajax_caller("",data,false,change_question_details);
updatecatview(cat);
}

function change_question_details(result){
	
Question=result.QUESTION;
optiona=result.OPTIONA;
optionb=result.OPTIONB;
optionc=result.OPTIONC;
optiond=result.OPTIOND;
answer=result.ANSWER;
QID=result.QID;
change_question_data();
}

function finish_test_confirm(){
var x;
var r=confirm("Are you sure to finish the test ?");
if (r==true)
  {
  finish_test();
}
document.getElementById("demo").innerHTML=x;

}
function finish_test()
{

}