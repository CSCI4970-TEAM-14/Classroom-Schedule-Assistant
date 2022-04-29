<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.sql.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="DB.BackupDB" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import Data</title>
</head>
<body>
<%
	BackupDB db = new BackupDB();

	db.Import();
%>

</body>
</html>