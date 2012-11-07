var qno;
var blurcount=4;

function fetch_first_question(categoryid){
	$.post("FetchNextQuestion",
			{ requestType : 'getFirstQuestion',
		      categoryId : categoryid
		
				},
			function(data,textStatus,xhr) {

				try {
					if(data.result===true) {
						var src = $("#changequehandle").html();
						var template = Handlebars.compile(src);
						var output = template(data);

						$("#question_portion").append(output);
						question_changed();
					}else if(data.result==='sectionAlreadyDoneError'){
						finish_section();
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
		});
}

function submit_question(answer){
	$.post("FetchNextQuestion",
			{ requestType : 'submitQuestion',
		      'answer' : answer
		
				},
			function(data,textStatus,xhr) {

				try {
					if(data.result===true) {
						var src = $("#changequehandle").html();
						var template = Handlebars.compile(src);
						var output = template(data);

						$("#question_portion").append(output);
						question_changed();
					}else if(data.result==='sectionAlreadyDoneError'){
						finish_section();
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
		});
}

function skip_question(){
	$.post("FetchNextQuestion",
			{ requestType : 'skipQuestion'
				},
			function(data,textStatus,xhr) {

				try {
					if(data.result===true) {
						var src = $("#changequehandle").html();
						var template = Handlebars.compile(src);
						var output = template(data);

						$("#question_portion").append(output);
						question_changed();
					}else if(data.result==='sectionAlreadyDoneError'){
						finish_section();
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
		});
}

function get_categories(){
	$.post("GetCategories",
			{ requestType : "gettests" },
			function(data,textStatus,xhr) {

				try {
					if(data.result===true) {
						var src = $("#getcategories").html();
						var template = Handlebars.compile(src);
						var output = template(data);
						$("#categories").html(output);
					}else if(data.result==='testFinished'){
						finish_test();
					}
				} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
		});
}

/*
$.post("GetCategories",
		{ requestType : "gettests" },
		function(data,textStatus,xhr) {

			try {
				if(data.result===true) {
					var src = $("#getcategories").html();
					var template = Handlebars.compile(src);
					var output = template(data);
					$("#categories").html(output);
				}
			} catch(e) { bootbox.alert(e.status+"\n"+e.message); }
	});*/


function question_changed(){
	qno++;
	$("#QNO").html(qno);
}

function set_counter(starttime){
	$('#countdowntimer').countdown({
        timestamp : new Date().getTime()+starttime,
        callback    : function(days, hours, minutes, seconds){
        	if(days===0&&hours===0&&minutes===0&&seconds===0){
        		finish_section();
        	}
        }
      });
}

function finish_section(){
	get_categories();
	show_catselector();
}


function start_cat(id,time){
	$("#cat"+id).hide();
	set_counter(time);
	fetch_first_question(id);
	hide_catselector();
}

function submit_next(){
	var ans = $("input[name=Answer]:checked","#Options").val();
	if(ans!=null)
	{
	submit_question(ans);
	}else if($("#Qstub"+category+qno).hasClass('btn-success'))
	{
		skip_question();
	}
}

function any(result){
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


function skip(){
	skip_question();
}

function show_catselector(){
	$("#question_portion").hide();
	$("#Nav_Bar").hide();
	$("#Controls").hide();
	$("#catselector").show();
}

function hide_catselector(){
	qno=0;
	$("#question_portion").show();
	$("#Nav_Bar").show();
	$("#Controls").show();
	$("#catselector").hide();
}


function clear_answer(){		
		$("#option1").attr("checked",false);
		$("#option2").attr("checked",false);
		$("#option3").attr("checked",false);
		$("#option4").attr("checked",false);
}

function disableF5(e) { if (e.which) e.preventDefault(); };



$(window).bind('keypress',disableF5);
$(window).bind('keydown',disableF5);
$(window).bind('keyup',disableF5);


$(window).blur(function(){
	blurcount--;
	if(blurcount<=0){
		finish_test();
		return;
	}
	alert('Go outside this window '+blurcount+' more times and I will Submit Your Test');
});

$(window).bind("contextmenu",function(e){
    return false;
});

$(document).ready(function(){
	
	get_categories();
	
	show_catselector();
});

function finish_test_confirm(){
	show_catselector();
	return;
var r=confirm("Are you sure to finish the test ?");
if (r==true)
  {
  finish_test();
}

}
function finish_test()
{
	$("form").submit();
}