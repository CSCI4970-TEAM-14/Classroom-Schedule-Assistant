<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
        section::after {
            content: "";
            display: table;
            clear: both;
        }
        
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        
        td,
        th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 15px;
        }
        
        tr:nth-child(even) {
            background-color: #dddddd;
        }
        
        * {
            box-sizing: border-box;
        }
        
        aside {
            float: left;
            padding: 20px;
            width: 70%;
            background-color: #cec8c8;
            height: 300px;
            /* only for demonstration, should be removed */
        }
        
        .center {
            margin-left: auto;
            margin-right: auto;
        }
        
        body {
            margin: 0;
        }
        /* Style the header */
        
        .header {
            background-color: rgb(19, 16, 16);
            padding: 20px;
            text-align: center;
            color: #f2f2f2;
        }
        /* Style the top navigation bar */
        
        .topnav {
            overflow: hidden;
            background-color: rgb(165, 22, 22);
        }
        
        .container {
            padding: 10px;
        }
        /* Style the topnav links */
        
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        /* Style the footer */
        
        footer {
            background-color: rgb(19, 16, 16);
            padding: 10px;
            text-align: center;
            color: white;
        }
        /* Change color on hover */
        
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }
        
        .h {
            background-color: rgb(252, 241, 241);
            padding: 6px;
            color: rgb(179, 15, 15);
            text-align: center;
        }
    </style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Classroom Schedule View</title>
</head>
<body>
		<div class="header">
        <h1>Classroom Scheduler <img src="logo.jpg" alt="" width="45" height="45"></h1>
    </div>

    <div class="topnav">
        <a href="https://www.unomaha.edu">Home</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
    </div>

    <div class="h">
        <h2><i>PKI Classroom Schedule</i> </h2>
    </div>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
                                <th>Classroom</th>
                                <th>Course</th>
                                <th>Meeting</th>
                                <th>Instructor</th>
                            </tr>
                            <c:forEach var="schedule" items="${room}">
                                <tr>
                                    <td>
                                        <c:out value="${schedule.Rid}" />
                                    </td>
                                    <td>
                                        <c:out value="${schedule.Cid}" />
                                    </td>
                                    <td>
                                        <c:out value="${schedule.Ctitle}" />
                                    </td>
                                    <td>
                                        <c:out value="${schedule.day}" />
                                        <c:out value="${schedule.start}" />
                                        <c:out value="${schedule.end}" />
                                    </td>
                                    <td>
                                        <c:out value="${schedule.Instname}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </body>

                </html>