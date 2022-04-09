<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="javax.servlet.RequestDispatcher"%>
<%@page import="javax.servlet.ServletException" import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<%@page import="entities.Classroom"%>
<%@page import="entities.Schedule"%>
<%@page import="entities.Course"%>
<%@page import="entities.Instructor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<title>Homepage</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSA_1.css">
<link rel="stylesheet" href="CSA_2.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="header">
		<h1>
			Classroom Scheduler <img src="logo.png" alt="" width="45" height="45">
		</h1>
	</div>
	<div class="topnav">
		<a href="https://www.unomaha.edu">Home</a> <a href="signUp.html">Register</a>
		<a href="login.jsp">Login</a>
	</div>

	<div class="h">
		<h2>
			<i>PKI</i>
		</h2>
	</div>

	<table class="center">
		<tr>
			<th>Classroom</th>
			<th>Course</th>
			<th>Meeting</th>
			<th>Instructor</th>
		</tr>
		<%
		@SuppressWarnings("unchecked")
		List<Classroom> list1 = (List<Classroom>) request.getAttribute("list1");
		@SuppressWarnings("unchecked")
		List<Course> list2 = (List<Course>) request.getAttribute("list2");
		@SuppressWarnings("unchecked")
		List<Schedule> list3 = (List<Schedule>) request.getAttribute("list3");
		@SuppressWarnings("unchecked")
		List<Instructor> list4 = (List<Instructor>) request.getAttribute("list4");
		
		Iterator <Classroom> itr1 = list1.iterator();
		Iterator <Course> itr2 = list2.iterator();
		Iterator <Schedule> itr3 = list3.iterator();
		Iterator <Instructor> itr4 = list4.iterator();
		%>
		<tr>
		<% while (itr1.hasNext()) {
			Classroom cl = (Classroom) itr1.next();%>
			<td><%=cl.getId()%></td>
		<%}%>
		<% while (itr2.hasNext()) {
			Course c = (Course) itr2.next();%>
			<td><%=c.getTitle()%></td>
		<%}%>
		<% while (itr3.hasNext()) {
			Schedule s = (Schedule) itr3.next(); %>
			<td><%=s.getDay()%></td>
		<%}%>
		<% while (itr4.hasNext()){
			Instructor in = (Instructor) itr4.next();%>
			<td><%=in.getFirstName()+" "+in.getLastName()%></td>
		<%}%>		
		</tr>
	</table>
</body>
</html>
