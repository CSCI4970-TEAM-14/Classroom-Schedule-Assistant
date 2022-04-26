<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Section</title>
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
        <h2>Add Section</h2>
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
    			<p style="color:red">**Section not added!**</p>
    		<%
    		}
    		%>
    		<%
    		if (session.getAttribute("Added") != null) {
    			session.setAttribute("Added", null);
    		%>
    			<p style="color:green">**Added Section!**</p>
    		<%
    		}
    		%>
						
			<%if (session != null){
					session.invalidate();} %>
					 
            <form action="./addSection" method="post">
                <div class="container">
                    <label for="crs"><b>Course Id</b></label>
                    <input type="text" placeholder="" name="crs" required>

                    <label for="sec"><b>Section Id</b></label>
                    <input type="text" placeholder="" name="sec" required>

                    <label for="meth"><b>Method</b></label>
                    <input type="text" placeholder="" name="meth" required>
                    
                    <label for="enr"><b>Enroll</b></label>
                    <input type="text" placeholder="" name="enr" required>
                    
                    <label for="inst"><b>Instructor</b></label>
                    <input type="text" placeholder="" name="inst" required>
                    
                    <label for="term"><b>Term</b></label>
                    <input type="text" placeholder="" name="term" required>

                    <button type="submit" style="color:white">Add</button>
                </div>
            </form>
        </article>
    </section>


</body>

</html>