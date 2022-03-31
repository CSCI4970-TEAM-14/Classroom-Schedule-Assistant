<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="servlets.Login" %>
<%@ page import="entities.Account" %>
<%@ page import="util.UtilAccount" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Classroom Scheduler Assistant Homepage</title>
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
                    <li class="b"><a href="userHome.jsp" style="color: white">Add</a></li>
                    <li class="b"><a href="userHome.jsp" style="color: white">Remove</a></li>
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
                </tr>
                <tr>
                    <td>Room 217</td>
                    <td>CSCI 4500</td>
                    <td>M/W 8:30 AM - 10:00 AM</td>
                    <td>Prof. Garfield</td>
                </tr>
                <tr>
                    <td>Room 120</td>
                    <td>MIS 1320</td>
                    <td>T/TH 1:00 PM - 2:15 PM</td>
                    <td>Ms. Scooby</td>
                </tr>
                <tr>
                    <td>Room 209</td>
                    <td>ISQA 2000</td>
                    <td>T/TH 5:30 PM - 7:30 PM</td>
                    <td>Dr. Courage</td>
                </tr>
                <tr>
                    <td>Room 103</td>
                    <td>ISQA 4210</td>
                    <td>T/TH 2:30 PM - 5:00 PM</td>
                    <td>Ms. Piggy</td>
                </tr>
                <tr>
                    <td>Room 156</td>
                    <td>CSCI 4220</td>
                    <td>M/W 5:30 PM - 7:30 PM</td>
                    <td>Prof. Scrappy</td>
                </tr>
                <tr>
                    <td>Room 200</td>
                    <td>ISQA 2000</td>
                    <td>T 7:30 AM - 9:30 AM</td>
                    <td>Dr. Java</td>
                </tr>
            </table>
        </article>
    </section>

</body>

</html>