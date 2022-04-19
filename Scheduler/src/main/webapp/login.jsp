<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSA_2.css">
<link rel="stylesheet" href="CSA_1.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<div class="header">
		<h1>Classroom Scheduler</h1>
	</div>

	<div class="topnav">
		<a href="scheduleView.jsp">Home</a> <a href="register.jsp">Register</a> <a href="https://www.unomaha.edu">UNO</a>
	</div>

	<div class="column middle">
		<h2 style="color: red">Login</h2>
					
			
			<%if (session.getAttribute("invalid") != null){
					session.setAttribute("invalid", null); %>
					<p><i>..Wrong userId or Password! Enter again..</i></p>
			<% } %>
			
			
			<%if (session != null){
					session.invalidate();} %>

		<form action="./log" method="POST">
			<div class="imgcontainer">
				<i class="glyphicon glyphicon-user"></i>
			</div>
			
			<div class="container">
				<label for="Username"><b>Username</b></label>
				<input type="text"	placeholder="Enter Username" name="Username" required>
			    <label for="pwd"><b>Password</b></label>
			    <input type="password" placeholder="Enter Password" name="pwd" required>

				<button type="submit" style="color: white">Login</button>
			</div>

			<div class="container"
				style="background-color: #f1f1f1; border: 1px solid white;">
				<button type="button" class="cancelbtn">Cancel</button>
				<span class="pws">Forgot <a href="#">password?</a></span>
			</div>
		</form>
	</div>

	<div class="footer">
		<p>Footer</p>
	</div>

</body>
</html>