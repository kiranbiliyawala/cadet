// Avoid `console` errors in browsers that lack a console.
if (!(window.console && console.log)) {
    (function() {
        var noop = function() {};
        var methods = ['assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'markTimeline', 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn'];
        var length = methods.length;
        var console = window.console = {};
        while (length--) {
            console[methods[length]] = noop;
        }
    }());
}

// Place any jQuery/helper plugins in here.


// Global Variables & Function

function databaseErrorDisplay(databaseErrorPage) {

	$.post(databaseErrorPage,function(data,textStatus,xhr) {

		try {
			if(textStatus==="success") {
				$("title").html("Database Error :(");
	
				$("html").css("padding","30px 10px");
				$("html").css("font-size","20px");
				$("html").css("line-height","1.4");
				$("html").css("color","#737373");
				$("html").css("background","#f0f0f0");
				$("html").css("-webkit-text-size-adjust","100%");
				$("html").css("-ms-text-size-adjust","100%");
	
				$("body").css("max-width","500px");
				$("body").css("_width","500px");
				$("body").css("padding","30px 20px 50px");
				$("body").css("border","1px solid #b3b3b3");
				$("body").css("border-radius","4px");
				$("body").css("margin","0 auto");
				$("body").css("box-shadow","0 1px 10px #a7a7a7, inset 0 1px 0 #fff");
				$("body").css("background","#fcfcfc");
	
				$("body").html(data);
			}
			else alert("Resource Not Available");
		} catch(e) { alert(e.status+"\n"+e.message); }
	});
}

function serverExceptionDisplay(serverExceptionPage) {

	$.post(serverExceptionPage,function(data,textStatus,xhr) {

		try {
			if(textStatus==="success") {
				$("title").html("Server Exception :(");
	
				$("html").css("padding","30px 10px");
				$("html").css("font-size","20px");
				$("html").css("line-height","1.4");
				$("html").css("color","#737373");
				$("html").css("background","#f0f0f0");
				$("html").css("-webkit-text-size-adjust","100%");
				$("html").css("-ms-text-size-adjust","100%");
	
				$("body").css("max-width","500px");
				$("body").css("_width","500px");
				$("body").css("padding","30px 20px 50px");
				$("body").css("border","1px solid #b3b3b3");
				$("body").css("border-radius","4px");
				$("body").css("margin","0 auto");
				$("body").css("box-shadow","0 1px 10px #a7a7a7, inset 0 1px 0 #fff");
				$("body").css("background","#fcfcfc");
	
				$("body").html(data);
			}
			else alert("Resource Not Available");
		} catch(e) { alert(e.status+"\n"+e.message); }
	});
}