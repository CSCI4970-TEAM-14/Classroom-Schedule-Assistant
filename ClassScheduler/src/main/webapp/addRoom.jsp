<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>Add Classroom</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="userpages.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        @media screen and (max-width: 600px) {
            .topnav .search-container {
                float: center;
            }
            .topnav a,
            .topnav input[type=text],
            .topnav .search-container button {
                float: none;
                display: block;
                text-align: left;
                width: 10%;
                margin: 0;
                padding: 14px;
            }
        }
    </style>
</head>

<body>

    <header>
        <h1>Class Scheduler Assistant<img src="logo.jpg" alt="" width="45" height="45"></h1>
    </header>

    <div class="topnav">
        <a href="userHome.jsp">Home</a>
        <a href="update.jsp">Return</a>
    </div>

    <div class="h">
        <h2>Add Room</h2>
    </div>

    <section>
        <nav>
            <div class="topnav">
                <a href="allClass.jsp">View All Classrooms</a>
                <a href="updateClass.jsp">Update Classrooms</a>
                <a href="adjust.jsp">Adjust Capacity</a>
            </div>
        </nav>
        <article>
            <form action="./addRoom" method="post">
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
            </form>
        </article>
    </section>


</body>

</html>