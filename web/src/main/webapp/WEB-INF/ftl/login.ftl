<#import "spring.ftl" as spring />

<!DOCTYPE html>
<html>
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap.css'/>"/>
	<title>Spring Exercise</title>
</head>
<body>
	<div class="container" >
		<div style="padding:20% 35%">
			<h2><center>Log In</center></h2>
			<form action = "/login/authenticate" method="POST" class="form-signin">
				<#if RequestParameters.error??><div class="alert alert-danger text-center">Invalid credentials</div></#if>
				<#if RequestParameters.logout??><div class="alert alert-success text-center">Successfully Logged Out</div></#if>
				<div class="form-group">
					<label for="username">Username:</label>
					<input type="text" class="form-control" id="username" name="username" required>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				
				<button type="submit" class="btn btn-primary center-block">Submit</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
					<br><br>
					<p align="center"><a href="${ctx.contextPath}/create_account">Create Account</a></p>
		</div>
	</div>
</body>
</html>