
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="../../../js/jquery-1.8.2.js"></script>
<script src="../../../js/bootstrap.js"></script>
<script src="js/NATEST.js"></script>
<link rel="stylesheet" type="text/css" href="../../../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/NATEST.css">
<link rel="stylesheet" type="text/css" href="/cadet/client/css/jquery.countdown.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%= request.getAttribute("TestName") %></title>

<script type="text/javascript">

var questiondistribution = <%= request.getAttribute("CatDistribution")%>;
var Time= '<%= request.getAttribute("TimeDuration")%>';
</script>

<script type="text/javascript" src="/cadet/client/js/jquery.countdown.js"></script>


</head>
<body class="body">
	<div id = "catselector" class="container accordion question_portion">
	<div style="margin-top: 5%"></div>
	<div class="Category" style="font-size: 20px;margin-bottom: .5%"> Categories </div>
	<%= request.getAttribute("Catselector") %>
	</div>
	<div class="container question_portion" id = "question_portion">
		<div class="Question_no">
        <h1 class="QNO" id="QNO">1</h1>
         <br/>
         <form id="flag_form" class="flag_check">
            <input type="checkbox" id="flag"/>
			Mark <br />Question<br /> as flaged
         </form>
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
        <br />
       
        <div class="Section" id="Section">
        <%= request.getAttribute("Queselector") %>
        </div>
      
        	
       <div class="Legend"> 
    <div class="cover">
    <div  class="stub btn-success" id="Attempted"></div>Attempted</div>
    <div class="cover">
    <div class="stub btn-danger" id="NotAttempted"></div>
    Not Attempted
    </div>
    <div class="cover">
    <div class="stub btn-warning" id="Flagged"></div>
    Flagged
    </div>
    </div>
      
	<div class="container Controls" id = "Controls">
		<button class="btn butn btn-primary" type="button" onclick="previous()" name="Previous" text="Previous" style="float:left;"/>&lt; Previous</button>
        <button class="btn butn btn-warning" type="button" onclick="show_catselector()" name="Change Section" text="Change Section"/>Change Section</button>
		<button class="btn butn btn-danger"  type="button" onclick="finish_test_confirm()" name="Finish Test" text="Finish Test"/>Finish Test</button>
        <button class="btn butn btn-primary" type="button" onclick="submit_next()" name="Next&ampSubmit" text="Next &amp Submit" style="float:right"/>Next &amp Submit &gt;</button>
        <button class="btn butn btn-info" type="button" onclick="skip()" name="Skip" text="Skip" style="float:right"/>Skip</button>
	</div>
</body>
</html>
