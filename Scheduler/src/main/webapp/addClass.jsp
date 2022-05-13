<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Classroom</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="userpages.css">
	
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
                <a href="classView.jsp">View All Classrooms</a>
                <a href="update.jsp">Update Classrooms</a>
                <a href="remove.jsp">Remove Classrooms</a>
            </div>
        </nav>
        <article>
        <%
    		if (session.getAttribute("Failed") != null) {
    			session.setAttribute("Failed", null);
    		%>
    			<p style="color:red">**Classroom NOT added!**</p>
    		<%
    		}
    		%>
    		<%
    		if (session.getAttribute("Added") != null) {
    			session.setAttribute("Added", null);
    		%>
    			<p style="color:green"><b>**Classroom ADDED!**</b></p>
    		<%
    		}
    		%>
						
			<%if (session != null){
					session.invalidate();} %>
					 
            <form action="./addRoom" method="post">
                <div class="container">
                    <label for="room"><b>Room number</b></label>
                    <input type="text" placeholder="" name="room" required>

                    <label for="type"><b>Type</b></label>
                    <input type="text" placeholder="" name="type" required>

                    <label for="seat"><b>Seats</b></label>
                    <input type="text" placeholder="" name="seat" required>

                    <button id="adjustBtn" type="submit" style="color:white">Add</button>
                </div>
            </form>
        </article>
    </section>


</body>

</html>