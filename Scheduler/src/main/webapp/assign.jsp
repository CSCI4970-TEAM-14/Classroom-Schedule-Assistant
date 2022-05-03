<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.sql.*"%>
<%@ page import="DB.BackupDB" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Data</title>
</head>
<body>
<%
 BackupDB db = new BackupDB();

 db.Assign();
%>

</body>
</html>