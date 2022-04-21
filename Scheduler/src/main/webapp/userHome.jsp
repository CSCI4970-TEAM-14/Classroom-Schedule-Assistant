<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
    <header>
        <h2>Class Scheduler Assistant<img src="logo.jpg" alt="" width="45" height="45"></h2>
    </header>

    <div class="topnav">
        <a href="scheduleView.jsp">Home</a>
        <a href="login.jsp">Logout</a>
    </div>

    <section>
    <%Account acc = null; %>
        <h1>Welcome <%request.getAttribute("name"); %>!</h1>
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
        </article>
    </section>
</body>
</html>