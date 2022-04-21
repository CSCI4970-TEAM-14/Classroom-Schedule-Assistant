<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlets.Log" %>
<%@ page import="entities.Schedule" %>
<%@ page import="DB.DBConnection" %>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Update Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSA_2.css">
<link rel="stylesheet" href="userpages.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

    <header>
        <h2>Classroom Scheduler Assistant <img src="logo.jpg" alt="" width="45" height="45"></h2>
    </header>

    <div class="topnav">
        <a href="userHome.jsp">Home</a>
        <a href="addClass.jsp">Add Classrooms</a>
        <a href="addSchedule.jsp">Add Schedules</a>
        <a href="adjustClass.jsp">Adjust Capacity</a>
        <a href="schedule.jsp">Scheduler</a>
    </div>

    <footer>
        <p></p>
    </footer>

</body>

</html>