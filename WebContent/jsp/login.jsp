<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title> Login</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div align = "center"><h4>Login to Insert and View Details</h4></div>
	<section class="loginform cf">
		
		<form method="post" action="login">
			<p>
				<input type="text" name="name" value="" placeholder="Username">
			</p>
			<p>
				<input type="password" name="password" value=""
					placeholder="Password">
			</p>
			
			<p class="submit">
				<input type="submit" name="commit" value="Login">
			</p>
		</form>
	</section>
</body>
</html>



