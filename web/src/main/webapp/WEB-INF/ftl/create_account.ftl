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
			<center><h1>Create Account </h1></center>
			<br>
				<#if success??>
				    <div class="alert alert-success text-center" role="alert">${success}</div>
				</#if>
			<form action="/create_account/submit" method="POST">
				<div class="form-group">
					<label for="username">Username:</label>
					<input type="text" class="form-control" id="username" name="username" required>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				<div class="form-group">
					<label for="role">Role:</label>
					<select class="form-control" name="role">
						<option value="USER"> User </option>
						<option value="ADMIN"/> Admin </option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary center-block">Submit</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        	</form>
				<br><br><center><a href="/login"> Back to Log In Page </a></center>
		</div>
	</div>
</body>
</html>