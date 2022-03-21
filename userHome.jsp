<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Update</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            box-sizing: border-box;
        }
        
        body {
            margin: 0;
        }
        /* Style the header */
        
        .header {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
        /* Style the top navigation bar */
        
        .topnav {
            overflow: hidden;
            background-color: #333;
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
        
        .footer {
            color: white;
            background-color: black;
            padding: 10px;
            height: 80px;
            width: 100%;
            text-align: center;
            border: 2px solid grey;
        }
        /* Change color on hover */
        
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }
    </style>
    <link rel="stylesheet" href="Login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
    <header>
        <h2>Class Scheduler Assistant</h2>
    </header>

    <section>
        <nav>
            <ul>
                <li><a href="/webapp/calendar.jsp">View Schedule Calendar</a></li>
                <li><a href="/webapp/update.jsp">Update Schedules</a></li>
                <li>
                    <a href="/webapp/Homepage.jsp"></a>
                </li>
                <li>
                    <a href="/webapp/logout.jsp"></a>Logout</li>
            </ul>
        </nav>

        <article>
            <h1>Welcome Username</h1>
            <p>Current news.</p>
            <p><a href="/webapp/Do.html"></a>To do list</p>
        </article>
    </section>

    <footer>
        <p>Footer</p>
    </footer>

</body>

</html>