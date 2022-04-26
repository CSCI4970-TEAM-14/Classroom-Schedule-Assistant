<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlets.Log"%>
<%@ page import="entities.Schedule"%>
<%@ page import="DB.DBConnection"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="out.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Classroom Schedule View</title>
</head>
<body>
	<div class="header">
		<h1>
			Classroom Scheduler <img src="logo.jpg" alt="" width="45" height="45">
		</h1>
	</div>

	<div class="topnav">
		<a href="https://www.unomaha.edu"><i class="fa fa-home"></i>Home</a> <a href="register.jsp">Register</a>
		<a href="login.jsp">Login</a>
	</div>

	<div class="h">
		<h2>
			<i>PKI Classroom Schedule</i>
		</h2>
	</div>

	<sql:setDataSource var="Schedule" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:3306/CSA?useSSL=false"
		user="nangatid" password="TKey" />

	<sql:query var="ListSchedule" dataSource="${Schedule}">
        SELECT * FROM Schedule;
    </sql:query>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Course</th>
				<th>Section</th>
				<th>Method</th>
				<th>Enrollment</th>
				<th>Instructor</th>
				<th>Meeting</th>
				<th></th>
				<th>Classroom</th>
			</tr>
			<c:forEach var="sched" items="${ListSchedule.rows}">
				<tr>
					<td><c:out value="${sched.course}" /></td>
					<td><c:out value="${sched.section}" /></td>
					<td><c:out value="${sched.method}" /></td>
					<td><c:out value="${sched.enroll}" /></td>
					<td><c:out value="${sched.instructor}" /></td>
					<td><c:out value="${sched.day}" /></td>
					<td><c:out value="${sched.start}"/> - <c:out value="${sched.end}" /></td>
					<td><c:out value="${sched.room}" /></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>