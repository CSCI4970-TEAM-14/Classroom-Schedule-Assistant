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
<title>Export/Import Page</title>
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
        <a href="update.jsp">Update Data</a>
        <a href="schedule.jsp">View Schedules</a>
    </div>
    
    
    <a href="export2.jsp" download="schedule">
       <button type="submit" style="color: white">Export Data</button>
   </a>

<div class="h">
        <h2><i>PKI Classroom Schedule</i> </h2>
    </div>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
                                <th>Classroom</th>
                                <th>Course</th>
                                <th> Section</th>
                                <th>Meeting</th>
                                <th>Instructor</th>
                            </tr>
                            <c:forEach var="sched" items="${ListSchedule.rows}">
                                <tr>
                                    <td>
                                        <c:out value="${sched.room}" />
                                    </td>
                                    <td>
                                        <c:out value="${sched.courseId}" />
                                    </td>
                                    <td>
                                        <c:out value="${sched.section}" />
                                    </td>
                                    <td>
                                        <c:out value="${sched.instructor}" />
                                    </td>
                                    <td>
                                        <c:out value="${sched.day}"/> 
                                        <c:out value="${sched.start}"/> 
                                        <c:out value="${sched.end}"  />
                                    </td>
                                
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

    <footer>
					<p></p>
    </footer>

    <footer>
        <p></p>
    </footer>