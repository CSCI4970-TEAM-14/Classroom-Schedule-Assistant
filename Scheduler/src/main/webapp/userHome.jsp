<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlets.Log" %>
<%@ page import="entities.Account" %>
<%@ page import="DB.DBConnection" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Classroom Scheduler Assistant Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="userpages.css">
</head>
<body>
    <header>
        <h2>Class Scheduler Assistant<img src="logo.jpg" alt="" width="45" height="45"></h2>
    </header>

    <div class="topnav">
        <a href="homepage.html">Home</a>
        <a href="login.jsp">Logout</a>
    </div>

    <section>
    <%Account acc = null; %>
        <h1>Welcome <%= request.getParameter("name") %>!</h1>
        <nav>
            <ul>
                <div class="relative">
                <li class="b"><a href="update.jsp" style="color: white">Update </a></li>
                <li class="b"><a href="remove.jsp" style="color: white">Remove</a></li>
                <li class="b"><a href="export.jsp" style="color: white">Export</a></li>
                </div>
            </ul>
        </nav>

        <article>
            <table class="center">
                <tr>
				<th>Classroom</th>
				<th>Course</th>
				<th>Meeting</th>
				<th>Instructor</th>
				<th>Actions</th>
			</tr>
			<tr>
				<c:forEach var="classroom" items="${room}">
					<td><c:out value="${classroom.id}" /></td>
				</c:forEach>
				<c:forEach var="course" items="${co}">
					<td><c:out value="${course.id}" /> <c:out
							value="${course.title}" /></td>
				</c:forEach>
				<c:forEach var="meeting" items="${sh}">
					<td><c:out value="${meeting.day}" /></td>
				</c:forEach>
				<c:forEach var="instructor" items="${in}">
					<td><c:out value="${instructor.first}" /> <c:out
							value="${instructor.last}" /></td>
				</c:forEach>
			</tr>
            </table>
        </article>
    </section>
</body>
</html>