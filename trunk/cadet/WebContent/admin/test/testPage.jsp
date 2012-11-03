<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>CADET | Test : <c:out value="${testName}"></c:out></title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="../../css/bootstrap.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

footer {
	background: none scroll repeat 0 0 #FFFFFF;
}
</style>
<link rel="stylesheet" href="../../css/bootstrap-responsive.css">
<link rel="stylesheet" href="../../css/main.css">

<script src="../../js/modernizr-2.6.1-respond-1.1.0.min.js"></script>
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="../index.jsp"><img src="../../img/cadet.gif" alt="CADET" width="66"></a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="../index.jsp">Home</a></li>
						<li><a href="#questionBank">Question Bank</a></li>
						<li class="active"><a href="testManagementHome.jsp">Test Management</a></li>
						<li><a href="#candidateCategory">Candidate Category</a></li>
					</ul>
					<button class="btn btn-danger pull-right">Logout</button>
				</div>
			</div>
			<!--/.container -->
		</div>
	</div>
	<!--/.navbar -->

	<div class="container-fluid">

		<div class="row">
			<div class="accordion span2" id="accordionMenu">

				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-parent="#accordionMenu" href="../index.jsp">Home</a>
					</div>
				</div>

				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse2">Question Bank</a>
					</div>
					<div id="collapse2" class="accordion-body collapse">
						<div class="accordion-inner">
							<ul class="nav">
								<li><a href="#">Add/View Questions</a></li>
								<li><a href="#">Upload Questions</a></li>
								<li><a href="#">Add/View Category</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse3">Test Management</a>
					</div>
					<div id="collapse3" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul class="nav">
								<li class="active"><a href="testManagementHome.jsp">View Test</a></li>
								<li><a href="createTest.jsp">Create Test</a></li>
								<li><a href="resultHome.jsp">Results</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionMenu" href="#collapse4">Candidate Category</a>
					</div>
					<div id="collapse4" class="accordion-body collapse">
						<div class="accordion-inner">
							<ul class="nav">
								<li><a href="#">Add Category</a></li>
								<li><a href="#">Edit Category</a></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<!--/#accordion -->

			<div class="container-fluid span9">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a class="brand" href="#editTest">Test : <c:out value="${testName}"></c:out>&nbsp;<span class="text-info"><small><em><c:out value="(${testType})"></c:out></em></small></span></a>
							<div style="margin:1.1%;" class="pull-right">
								<strong>Total Duration :&nbsp;&nbsp;</strong><span class="badge badge-info">&nbsp;&nbsp;0&nbsp;&nbsp;</span>&nbsp;&nbsp;Min.
							</div>
						</div>
					</div>
				</div>
				<div class="container-fluid span8">
					<div class="container-fluid">
						<button class="btn btn-primary pull-left">Add Category</button>
						<button class="btn btn-danger offset1">Remove Category</button>
						<button class="btn btn-primary offset1">Test Settings</button>
					</div>
					<hr>
					<form id="frmSaveTest" class="container-fluid form-horizontal" method="post" action="TestManagement">
						<table class="table table-striped table-condensed table-hover">
							<thead>
								<tr>
									<th></th>
									<th class="2">Category</th>
									<th class="6">Number of Questions</th>
									<th class="6">Duration</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty categoryDetails}">
										<c:forEach var="i" items="${categoryDetails}">
											<tr>
												<td><input type="checkbox" id="chkDelCatList" name="chkDelCatList" value='<c:out value="chk${i.categoryId}"></c:out>'></td>
												<td class="span2">
													<c:out value="${i.categoryName}"></c:out>
												</td>
												<td class="span6">
													<c:set var="TestAdaptive" value="Adaptive"></c:set>
													<c:set var="TestNonAdaptive" value="Non-Adaptive"></c:set>
													<c:choose>
														<c:when test="${testType  eq TestAdaptive}">
															<input class="" id='<c:out value="txtNoQueCat${i.categoryId}"></c:out>' name='<c:out value="txtNoQueCat${i.categoryId}"></c:out>' type="number" required placeholder="Questions per Category" min=0 value='<c:out value="${i.questionsPerCategory}"></c:out>'>
														</c:when>
														<c:otherwise>
															<button class="btn btn-primary">Add/View Questions</button>
														</c:otherwise>
													</c:choose>
												</td>
												<td class="span6">
													<div class="input-append">
														<input class="" id='<c:out value="txtTimeCat${i.categoryId}"></c:out>' name='<c:out value="txtTimeCat${i.categoryId}"></c:out>' type="number" required placeholder="In Minutes" min=0 value='<c:out value="${i.timePerCategory}"></c:out>'>
														<span class="add-on">Min.</span>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td><p class="text-warning">No Category Available</p></td>
											<td></td>
											<td></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>

						<div class="control-group pull-right">
							<div class="controls">
								<input type="hidden" id="requestType" name="requestType" value="saveTest">
								<input type="hidden" id="testId" name="testId" value="<c:out value="${testId}"></c:out>">
								<input type="hidden" id="testName" name="testName" value="<c:out value="${testName}"></c:out>">
								<input type="hidden" id="testType" name="testType" value="<c:out value="${testType}"></c:out>">
								<input type="submit" <c:if test="${not empty saveFlag}">disabled="disabled"</c:if> class="btn btn-success" value="Save">
								<a href="testManagementHome.jsp" class="btn btn-danger">Cancel</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--/.row div -->
	</div>
	<!--/.container div -->

	<footer class="container-fluid navbar navbar-fixed-bottom">
		<hr>
		<div class="row" style="margin-bottom: 10px;">
			<a class="span pull-right" href="#">About</a>
			<a class="span pull-right" href="#">FAQ</a>
			<a class="span pull-right" href="#">Contact us</a>
			<a class="span pull-right" href="#">Help</a>
			<a class="span pull-right" href="#">Privacy &amp; Policy</a>
		</div>
	</footer>

	<script src="../../js/jquery-1.8.2.js"></script>
	<script>window.jQuery || document.write('<script src="../../js/jquery-1.8.2.js"><\/script>')</script>

	<script src="../../js/bootstrap.js"></script>
	<script src="../../js/bootbox.js"></script>
	<script src="../../js/jquery.validate.js"></script>
	<script src="../../js/additional-methods.js"></script>
	<script src="../js/jsGlobal.js"></script>
	<script src="../js/jsTestPage.js"></script>
</body>
</html>
