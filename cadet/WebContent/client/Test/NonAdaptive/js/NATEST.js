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
	
	var internal = "#Section #Qstub"+cat+question+", #catselector #Qstub"+cat+question;

	if(command=="Attempted"){
		$(internal).addClass("Attempted");
		$(internal).removeClass("NotAttempted");
		$(internal).removeClass("Flagged");
	}
	else if(command=="Flagged"){
		$(internal).addClass("Flagged");
		$(internal).removeClass("NotAttempted");
		$(internal).removeClass("Attempted");
	}
	else if(command=="NotAttempted"){
		$(internal).addClass("NotAttempted");
		$(internal).removeClass("Attempted");
		$(internal).removeClass("Flagged");
	}
}

function updatecatview(cat){
	for(a in categories){
		$("#dispcat"+a).hide(0);
	}
	$("#dispcat"+cat).show(2);
}

function ajax_caller(callurl,data_value,async_value,callback){
	$.ajax({
		url: callurl,
		data: data_value,
		async: async_value,
		type:'POST',
		success: function(data) {
            callback(data);
        }
	}
		);
}


function show_cat(covercat){
	$(".CatCover:not(#"+covercat+")").hide();
}

function change_question_data(){
	$("#optn1").html(optiona);
	$("#optn2").html(optionb);
	$("#optn3").html(optionc);
	$("#optn4").html(optiond);
	$("#Question").html(Question);
	$("QNO").html(qno);
	
	if(answer=='A'){
	$("#option1").attr("checked",true);	
	}
	if(answer=='B'){
	$("#option2").attr("checked",true);	
	}
	if(answer=='C'){
	$("#option3").attr("checked",true);	
	}
	if(answer=='D'){
	$("#option4").attr("checked",true);	
	}
}


function submit_next(){
	if($("#flag").is(":checked")){
			remove_answer();
			set_flag();
			skip();
			return;
		}
		
	var ans = $("input[name=Answer]:checked","#Options").val();
	if(ans!=null)
	{
	submit_answer(ans);
	}else if($("#Qstub"+category+qno).hasClass('Attempted'))
	{
		remove_answer();
	}
		
	skip();
}

function any(result){
}

function remove_answer(){
	var dat = {
			'category': category,
			'QNO': qno,
			'QID': QID
	};
	ajax_caller("RemoveAnswer",dat,false,any);
	update_Attempted("NotAttempted",category,qno);
}

function submit_answer(ans){
	var dat = {
			'CATEGORY': category,
			'QNO': qno,
			'QID': QID,
			'ANS':ans
	};
	ajax_caller("SubmitAnswer",dat,false,any);
	update_Attempted("Attempted",category,qno);
}

function set_flag() {
	update_Attempted("Flagged",category,qno);
}


function skip(){
	var maxamount = questiondistribution[category];
	if(qno<maxamount){
		qno = qno+1;
		change_que(category, qno);
	}else{
		show_catselector();
	}
}

function previous(){
	if(qno>1){
		qno = qno-1;
		change_que(category, qno);
	}else{
		show_catselector();
	}
}

function show_catselector(){
	$(".CatCover").show();
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
	show_cat("cover"+cat);
	hide_catselector();
	clear_answer();
refresh_que(cat,queno);
}

function refresh_que(cat,queno)
{
var data={
	'CAT':cat,
	'QNO':queno
	};

category=cat;
qno=queno;
ajax_caller("GetQuestion",data,false,change_question_details);
}

function change_question_details(res){
	
	var result = JSON.parse(res);
	
Question=result.QUESTION;
optiona=result.OPTIONA;
optionb=result.OPTIONB;
optionc=result.OPTIONC;
optiond=result.OPTIOND;
answer=result.ANSWER;
QID=result.QID;
change_question_data();
}


function body_load(){
	show_catselector();
}

function finish_test_confirm(){
var r=confirm("Are you sure to finish the test ?");
if (r==true)
  {
  finish_test();
}

}
function finish_test()
{
	$("form").submit();
		alert("a");
}