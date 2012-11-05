<table id="tblTests" class="table table-striped table-condensed table-hover">
							<thead>
								<tr>
									<th>Test Name</th>
									<th>Date</th>
									<th>Duration</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
<script src="../js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/admindashboard.js">
</script>
 <script src="../js/jquery-1.8.2.js"></script>
        <script src="../js/bootstrap.js"></script>
        <script src="../js/handlebars.js"></script>
        <script src="../js/bootbox.js"></script>

<script id="gettests" type="text/x-handlebars-template">
        	{{#if testList}}
				{{#each testList}}
					<tr>
						<td>{{testName}}</td>
						<td>{{#if testDate}}{{testDate}}{{else}}NA{{/if}}</td>
						<td>{{testDuration}} Mins.</td>
						<td>
						</td>
					</tr>
				{{/each}}
			{{else}}
				<tr>
					<td><p class="text-warning">No Test Available</p></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			{{/if}}
    	</script>
</html>