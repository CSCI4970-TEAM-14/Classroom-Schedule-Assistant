<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
    
    <form action="/ExpImpo" method="get">
		<label for="select">Choose data to export from the list:</label> <input
			list="selections" name="select" id="select">
		<datalist id="selections">
			<option value="All Schedules">
			<option value="Classroom" <a href="addSchedule.jsp"></a>> 
			<option value="All Classroom">
		</datalist>
  <input type="submit">
</form>

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

    <footer>
					<p></p>
    </footer>

    <footer>
        <p></p>
    </footer>