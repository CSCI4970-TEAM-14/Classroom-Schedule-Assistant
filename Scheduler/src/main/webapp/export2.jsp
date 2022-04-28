<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Export Data</title>
</head>
<body>
	<%
String filename = "c:\\csv\\Csvfile.csv";
Connection conn = null;
String url = "jdbc:mysql://ec2-3-129-194-150.us-east-2.compute.amazonaws.com:3306/CSA?useSSL=false";
//String dbName = "CSA";
String driver = "com.mysql.jdbc.Driver";
String userName = "nangatid";
String password = "TKey";
Statement statement;
try {
	FileWriter fw = new FileWriter(filename);
	fw.append("Schedule id");
	fw.append(',');
	fw.append("Room no.");
	fw.append(',');
	fw.append("Course id");
	fw.append(',');
	fw.append("Section id");
	fw.append(',');
	fw.append("Instructor");
	fw.append(',');
	fw.append("Meeting");
	fw.append('\n');

	Class.forName(driver);
	conn = DriverManager.getConnection(url, userName, password);
	String query = "SELECT * FROM Schedule";
	statement = conn.createStatement();
	ResultSet rs = statement.executeQuery(query);
	while (rs.next()) {
		fw.append(rs.getString(1));
		fw.append(',');
		fw.append(rs.getString(2));
		fw.append(',');
		fw.append(rs.getString(3));
		fw.append(',');
		fw.append(rs.getString(4));
		fw.append(',');
		fw.append(rs.getString(5));
		fw.append(',');
		fw.append(rs.getString(6));
		fw.append('\n');
	}
	fw.flush();
	fw.close();
	conn.close();
	out.println("<b>You have Successfully Created Csv file.</b>");
} catch (Exception e) {
	e.printStackTrace();
}
%>

</body>
</html>