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
<html lang="en">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.btn {
  background-color: grey;
  border: none;
  color: white;
  padding: 12px 16px;
  font-size: 16px;
  cursor: pointer;
}

/* Darker background on mouse-over */
.btn:hover {
  background-color: RoyalBlue;
}
</style>
<head>
<title>Removal Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="CSA_2.css">
<link rel="stylesheet" href="input.css">
<link rel="stylesheet" href="userpages.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

    <header>
        <h2>Classroom Scheduler Assistant <img src="logo.jpg" alt="" width="45" height="45"></h2>
    </header>

    <div class="topnav">
        <a href="userHome.jsp"><i class="fa fa-home"></i>Home</a>
        <a href="schedule.jsp">Scheduler</a>
    </div>
    
    <div class="container">
    	
			<a href="removeClass.jsp" ><button class="btn"><i class="fa fa-folder"></i> Remove Classroom</button></a>
			<a href="removeSchedule.jsp"><button class="btn"><i class="fa fa-folder"></i> Remove Schedule</button></a>
			<a href="removeSection.jsp"><button class="btn"><i class="fa fa-folder"></i> Remove Section</button></a>
	</div>