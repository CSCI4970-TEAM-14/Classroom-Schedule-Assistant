<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
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
    
    <sql:setDataSource
        var="Schedule"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:3306/CSA?useSSL=false"
        user="nangatid" password="TKey"
    />
     
    <sql:query var="ListSchedule"   dataSource="${Schedule}">
        SELECT * FROM Schedule;
    </sql:query>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
                                <th>Classroom</th>
                                <th>Course</th>
                                <th> Section</th>
                                <th>Instructor</th>
                                <th>Meeting</th>
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
                </body>

                </html>