<#import "spring.ftl" as spring />
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap.css'/>"/>
	<title>Spring Exercise - Access Denied</title>
</head>
<body>
	<div class="container" style="padding: 5%">
		<div class="alert alert-danger" role="alert">
			<h3 class="alert-heading">HTTP ERROR 401 Unauthorized</h3>
			<p>You are not allowed to access this page. </p>
		</div>
		<div align="center"><a href="/main"> Back to Main Page </a></div>
	</div>
</body>
</html>