<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap.css'/>"/>
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/custom.css'/>"/>
        <title>Spring Exercise</title>
	</head>
	<body>
		<div class="container">
			<div class="header">
				 <img src="/resources/exist-logo1.png" alt="Exist"> 
				 <h1 class="title"><strong>Spring Exercise</strong></h1>
			</div>
			<div class="row">
				<div class="panel panel-default" id="filterBy">
					<div class="panel-heading"><strong>Filter By</strong></div>
					<div class="panel-body">
						<form action="${ctx.contextPath}">
						  <form>
							  <fieldset>
							  <table style="width:100%">
							  <tr>
								<td><input type="radio" name="filterBy" value="dateHired">Date Hired: </td>
								<td><input type="date" name="dateHired"><br></td>
								<td><input type="radio" name="filterBy" value="gwa">GWA: <br></td>
								<td><input type="number" name="gwa" step="0.01" max="5" min="1" ><br></td>
								<td><input type="radio" name="filterBy" value="lastName">Last Name: <br/></td>
								<td><input type="text" name="lastName"><br></td>
							  </tr>
							  <tr><td colspan="6" align="right"><input type="submit" class="btn btn-primary" value="Submit"/></td></tr>
							  </table>
							 </fieldset>
							</form>
						</form>
					</div>
				</div>
				<span style="color:green; font-weight: bold; text-align:center"><p>${deleteSuccessful!}</p></span>
				<table class="table table.hover">
					<thead>
						<th>ID</th>
						<th>Name</th>
						<th>Home Address</th>
						<th>Work Address</th>
						<th>Birthday</th>
						<th>Date Hired</th>
						<th>GWA</th>
						<th>Employed?</th>
						<th>Roles</th>
						<th>Contacts</th>
						<th></th>
						<th></th>
					</thead>
					<tbody>
						<#list persons as person>
							<tr>
								<td><b>${person.id}</b></td>
								<td><b>${person}</b></td>
								<td>${person.homeAddress}</td>
								<td>${person.workAddress}</td>
								<td>${person.birthday ? string["MMM dd, yyyy"]}</td>
								<td>${person.dateHired ? string["MMM dd, yyyy"]}</td>
								<td>${person.getGwa()}</td>
								<td><#if person.currentlyEmployed == true>Yes<#else>No</#if></td>
								<td>
									<#list person.roles as role>
										${role.roleName}<#sep>,<#else> None
									</#list>
								</td>
								<td>
									<#list person.contacts as contact>
										${contact.info}<br>
									</#list>
								</td>
								<td>
									<a href="${ctx.contextPath}/person/?action=edit&id=${person.id}">Edit</a> 
								</td>
								<td>
									<a onclick="return confirm('Are you sure?');" href="${ctx.contextPath}/delete?id=${person.id}">Delete</a> 
								</td>
							</tr>
						</#list>
					</tbody>
				</table>
				<div align="center">
					<form id="action" action="${ctx.contextPath}/person" method="get">
						<input type="hidden" name="action" value ="add"> 
						<input type="submit" class="btn btn-primary" value="Add Person"/>
					</form>
					<form id="action" action="${ctx.contextPath}/role" method="get">
						<input type="submit" class="btn btn-primary" value="Manage Roles"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
