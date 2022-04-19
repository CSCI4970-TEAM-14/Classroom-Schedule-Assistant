<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>Adjust</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="userpages.css">
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
        <a href="updateClass.jsp">Update Classrooms</a>
        <a href="addRoom.jsp">Add Classrooms</a>

    </div>

    <div class="h">
        <h2>Adjust Room Capacity</h2>
    </div>

    <section>
        <nav>
            <div class="topnav">
                <a href="adjust.jsp">Search Classroom / Type in ~></a>
                <div class="search-container">
                    <form action="/action_page.php">
                        <input type="text" placeholder="Search room.." name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>
        </nav>
        <%if (session.getAttribute("failed") != null){
                session.setAttribute("failed", null); %>
                <p style="color:red"><i>..Failed to Adjust Classroom Capacity!..</i></p>
                <% } %>

                    <%if (session.getAttribute("ajusted") != null){
                        session.setAttribute("adjusted", null); %>
                        <p style="color:green"><i>..Classroom Adjusted!..</i></p>
                        <% } %>
                        
                        <%if (session != null){
						session.invalidate();} %>
        <article>
            <form action="./adjustCapacity" method="post">
                <div class="container">
                    <label for="room"><b>Room number</b></label>
                    <input type="text" placeholder="" name="room" required>

                    <label for="building"><b>Building</b></label>
                    <input type="text" placeholder="" name="building" required>

                    <label for="seats"><b>Seats</b></label>
                    <input type="text" placeholder="" name="seats" required>

                    <button type="submit" style="color:white">Adjust</button>
                </div>

                <div class="container" style="background-color:#f1f1f1">
                    <button type="button" class="cancelbtn">Back</button>
                </div>
            </form>
        </article>
    </section>

    <footer>
        Footer
    </footer>



</body>

</html>