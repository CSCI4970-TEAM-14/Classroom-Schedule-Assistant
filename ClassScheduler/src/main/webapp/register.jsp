<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSA_2.css">
<link rel="stylesheet" href="CSA_1.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="header">
		<h1>Classroom Scheduler</h1>
	</div>

	<div class="topnav">
		<a href="homepage.html">Home</a> <a href="login.jsp">Login</a> <a href="https://www.unomaha.edu">UNO</a>
	</div>

	<h2 style="color: red">Account Registration</h2>
	
	<%if (session.getAttribute("Failed") != null){
					session.setAttribute("Failed", null); %>
					<p><i>..Failed to create account..</i></p>
			<% } %>
			
			
			<%if (session.getAttribute("inUse") != null){
					session.setAttribute("inUse", null); %>
					<p><i>..Account already exists!..</i></p>
			<% } %>
			
			
			<%if (session != null){
					session.invalidate();} %>

	<form action="./Login" method="POST">
		<div class="imgcontainer">
			<i class="glyphicon glyphicon-user"></i>
		</div>

		<div class="container">
			<label for="uname"><b>UserId</b></label> 
			<input type="text" placeholder="" name="uname" required>
			<label for="fname"><b>Firstname</b></label>
			<input type="text" placeholder="" name="fname" required> 
			<label for="lname"><b>Lastname</b></label>
			<input type="text" placeholder="" name="lname" required>
			<label for="email"><b>Email</b></label>
			<input type="text" placeholder="" name="email" required>
			<label	for="psw"><b>Password</b></label>
			<input type="password" placeholder="" name="psw" required>

			<button type="submit" style="color: white">Register</button>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
		</div>
	</form>

	<div class="footer">
		<p>Footer</p>
	</div>

</body>

</html>