<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add Classroom</title>
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
        <a href="userHomepage.jsp">Home</a>
        <a href="update.jsp">Return to Update</a>
        <a href="updateClass.jsp">Update Classrooms</a>
    </div>

    <h2 style="color: red">Add Classrooms</h2>

    <form action="./addRoom" method="post">
        <div class="imgcontainer">
            <i class="glyphicon glyphicon-user"></i>
        </div>

        <div class="container">
            <label for="room"><b>Room number</b></label>
            <input type="text" placeholder="" name="room" required>

            <label for="type"><b>Type</b></label>
            <input type="text" placeholder="" name="type" required>

            <label for="building"><b>Building</b></label>
            <input type="text" placeholder="" name="building" required>

            <label for="seats"><b>Seats</b></label>
            <input type="text" placeholder="" name="seats" required>

            <label for="com"><b>Computers</b></label>
            <input type="text" placeholder="" name="com" required>

            <button type="submit" style="color:white">Add</button>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" class="cancelbtn">Back</button>
        </div>
    </form>

    <div class="footer">
        <p>Footer</p>
    </div>

    <div style="background-color:green">
        <h3>Successfully added!</h3>

    </div>
</body>

</html>