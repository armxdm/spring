<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
			<div class="row" align="center">
				<h2>Roles</h2>
				<span style="color:green; font-weight: bold"><p>${deleteSuccessful!}</p></span>
				<table class="table table.hover" style="width:500px">
					<thead>
						<th>ID</th>
						<th>Name</th>
						<th></th>
						<th></th>
					</thead>
					<tbody>
						<#list roles! as role>
							<tr>
								<td><b>${role.id}</b></td>
								<td style="width:50%">${role.roleName}</td>
								<td>
									<a href="${ctx.contextPath}/role?action=edit&id=${role.id}">Edit</a> 
								</td>
								<td>
									<a onclick="return confirm('Are you sure?');" href="${ctx.contextPath}/role?action=delete&id=${role.getId()}">Delete</a> 
								</td>
							</tr>
						</#list>
					</tbody>
				</table>
				<#if (RequestParameters.action)! == "edit">
						<div class="panel panel-default" style="width:400px;">
							<div class="panel-heading"><b>Edit Role Name</b></div>
							<div class="panel-body">
								<form action="${ctx.contextPath}/role?action=edit&submit=true" method="post">
									<span style="color:green; font-weight: bold"><p>${editSuccessful!}</p></span>
									<input type="hidden" name="id" value ="${(role.id)!}"> 
									Role Name: <input type="text" name="roleName" value="${(role.roleName)!}">
									<input type="submit" class="btn btn-primary" value="Submit"/>
								</form>
							</div>
						</div>
						<p><a href="${ctx.contextPath}/role"> Add Role</a></p>
				<#else> 
					<div class="panel panel-default" style="width:400px;">
						<div class="panel-heading"><b>Add Role</b></div>
						<div class="panel-body">
							<form action="${ctx.contextPath}/role?action=add" method="post">
								<span style="color:green; font-weight: bold"><p>${addSuccessful!}</p></span>
								Role Name: <input type="text" name="roleName">
								<input type="hidden" name="action" value ="add"> 
								<input type="submit" class="btn btn-primary" value="Submit"/>
							</form>
						</div>
					</div>
				</#if>
				<a href="${ctx.contextPath}/"> Back to Main Page</a>
			</div>
		</div>
	</body>
</html>