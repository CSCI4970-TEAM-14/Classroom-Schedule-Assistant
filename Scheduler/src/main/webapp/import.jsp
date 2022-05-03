<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlets.Log"%>
<%@ page import="entities.Schedule"%>
<%@ page import="DB.DBConnection"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="DB.BackupDB"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Import Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="userpages.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
	<header>
		<h2>
			Classroom Scheduler Assistant <img src="logo.jpg" alt="" width="45"
				height="45">
		</h2>
	</header>

	<div class="topnav">
		<a href="userHome.jsp">Home</a> <a href="update.jsp">Update Data</a> <a
			href="schedule.jsp">View Schedules</a>
	</div>

	<%
                if (session.getAttribute("Failed") != null) {
                    session.setAttribute("Failed", null);
                %>
	<p style="color: red">**Could not import data!**</p>
	<%
                }
                %>

	<%
                if (session.getAttribute("Imported") != null) {
                    session.setAttribute("Imported", null);
                %>
	<p style="color: green">**Data imported!**</p>
	<%
                }
                %>

	<%if (session != null){
                        session.invalidate();} %>

		<div class="h">
        <h2>Import Data to Database</h2>
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
		
		<form action="./importServlet" method="post">
		<div class="container">
			<table >
				<tr>
					<td>Csv File:</td>
					<td><input type="file" name="xcl" size="50" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<button type="submit" style="color:white">Import</button>
					</td>
				</tr>
			</table>
		</div>
            </form>
        </article>
    </section>
</body>

</html>