<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="out.css">
<link rel="stylesheet" href="input.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="header">
		<h1>
		Classroom Scheduler <img src="logo.jpg" alt="" width="45" height="45">
		</h1>
	</div>

	<div class="topnav">
		<a href="scheduleView.jsp">Home</a> <a href="login.jsp">Login</a> <a href="https://www.unomaha.edu">UNO</a>
	</div>

	<h2 style="color: black">Account Registration</h2>
	
			<%
    		if (session.getAttribute("inUse") != null) {
    			session.setAttribute("inUse", null);
    		%>
    			<p style="color:red">**The username is already in use!**</p>
    		<%
    		}
    		%>
						
			<%if (session != null){
					session.invalidate();} %>

	<form action="Register" method="POST">
		<div class="imgcontainer">
			<i class="glyphicon glyphicon-user"></i>
		</div>

		<div class="container">
			<!-- <label for="UserId"><b>UserId</b></label> 
			<input type="text" placeholder="Enter id" name="UserId" required> -->
			<label for="username"><b>Username</b></label>
			<input type="text" placeholder="Enter username" name="username" required>
			<label for="firstName"><b>Firstname</b></label>
			<input type="text" placeholder="Enter firstname" name="firstName" required> 
			<label for="lastName"><b>Lastname</b></label>
			<input type="text" placeholder="Enter lastname" name="lastName" required>
			<label for="email"><b>Email</b></label>
			<input type="text" placeholder="Enter email" name="email" required>
			<label	for="password"><b>Password</b></label>
			<input type="password" placeholder="Enter password" name="password" required>
			<button id="subBtn" type="submit" style="color: white"><b>Register</b></button>
		</div>

		<div class="container" style="background-color: #f1f1f1;border: 1px solid white;">
			<button type="button" class="cancelbtn">Cancel</button>
		</div>
	</form>

	<div class="footer">
		<p>Footer</p>
	</div>

</body>

</html>