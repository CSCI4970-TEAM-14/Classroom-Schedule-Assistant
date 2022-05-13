<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="servlets.Log" %>
<%@ page import="entities.Schedule" %>
<%@ page import="entities.Account" %>
<%@ page import="DB.DBConnection" %>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Classroom Scheduler Assistant Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="userpages.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <header>
        <h2>Class Scheduler Assistant<img src="logo.jpg" alt="" width="45" height="45"></h2>
    </header>

    <div class="topnav">
        <a href="scheduleView.jsp"><i class="fa fa-home"></i>Home</a>
        <a href="login.jsp">Logout</a>
    </div>
    
    <%if (session.getAttribute("acc") != null){
					session.setAttribute("acc", null); %>
					<p style="color: green"><i>..You're in..</i></p>
			<% } %>

    <section>
    <%Account acc = null; %>
        <h1>Welcome ${acc.username}!</h1>
        <nav>
            <ul>
                <div class="relative">
                <li class="b"><a href="schedule.jsp" style="color: white">Assign</a></li>
                <li class="b"><a href="update.jsp" style="color: white">Update </a></li>
                <li class="b"><a href="remove.jsp" style="color: white">Remove</a></li>
                <li class="b"><a href="export.jsp" style="color: white">Export</a></li>
                <li class="b"><a href="import.jsp" style="color: white">Import</a></li>
                </div>
            </ul>
        </nav>
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

        <article>
            <table class="center">
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
        </article>
    </section>
</body>
</html>