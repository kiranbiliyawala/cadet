
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="../../../js/jquery-1.8.2.js"></script>
<script src="../../../js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="../../../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/ATEST.css">
<link rel="stylesheet" type="text/css" href="/cadet/client/css/jquery.countdown.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="icon" type="image/ico" href="../../../img/favicon.ico">

<script type="text/javascript" src="/cadet/client/js/jquery.countdown.js"></script>


</head>
<body class="body">
	<div id = "catselector" class="container accordion question_portion">
	<div style="margin-top: 5%"></div>
	<div class="Category" style="font-size: 20px;margin-bottom: .5%"> Categories </div>
	<div id="categories" ></div>
	</div>
	<div class="container question_portion" id = "question_portion">
		<div class="Question_no">
        <h1 class="QNO" id="QNO">1</h1>
         </div>
		<div class="Question" id="Question">
			Question
				A, B, C and D go for a picnic. When A stands on a weighing machine, B also climbs on, and the weight shown was 132 kg. When B 	stands, C also climbs on, and the machine shows 130 kg. Similarly the weight of C and D is found as 102 kg and that of B and D is 116 kg. What is D's weight??
		</div>
		<form id="Options" method="post" class="Options" action="SubmitTest">
			<input type="radio" name="Answer" id="option1" value="A"/><span id="optn1">Option1</span><br />
			<input type="radio" name="Answer" id="option2" value="B"/><span id="optn2">Option2</span><br />
			<input type="radio" name="Answer" id="option3" value="C"/><span id="optn3">Option3</span><br />
            <input type="radio" name="Answer" id="option4" value="D"/><span id="optn4">Option4</span><br />
            <br/>
                    <a href="javascript:void(0)" onclick="clear_answer()">Clear Answer</a>
         </form>
		</div>
</div>
	<div class="container Nav_Bar" id="Nav_Bar">
		
        <div class="Timer" >
	 <div id="countdowntimer"></div>
 	</div>
        
        	
  
      
	<div class="container Controls" id = "Controls">
		<button class="btn butn btn-danger"  type="button" onclick="finish_test_confirm()" style="float: left;" name="Finish Test" text="Finish Test"/>Finish Test</button>
        <button class="btn butn btn-primary" type="button" onclick="submit_next()" name="Next&ampSubmit" text="Next &amp Submit" style="float:right"/>Next &amp Submit &gt;</button>
        <button class="btn butn btn-info" type="button" onclick="skip()" name="Skip" text="Skip" style="float:right"/>Skip</button>
	</div>
	  <script src="/cadet/js/handlebars.js"></script>
       
	 <script id="getcategories" type="text/x-handlebars-template">
        	{{#if data}}
				{{#each data}}
					<div class="test_category" id="cat{{this.[0]}}" onclick="start_cat({{this.[0]}},{{this.[2]}})">
					{{this.[1]}}
					</div>
				{{/each}}
			{{/if}}
    	</script>
	<script src="js/ATEST.js"></script>
</body>
</html>
