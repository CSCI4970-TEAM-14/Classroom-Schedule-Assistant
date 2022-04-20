<!DOCTYPE html>
<html>

<head>
<title>Export</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="userpages.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
@media screen and (max-width: 600px) {
	.topnav .search-container {
		float: center;
	}
	.topnav a, .topnav input[type=text], .topnav .search-container button {
		float: none;
		display: block;
		text-align: left;
		width: 10%;
		margin: 0;
		padding: 14px;
	}
}
</style>
</head>

<body>

	<header>
		<h1>
			Class Scheduler Assistant<img src="logo.png" alt="" width="45"
				height="45">
		</h1>
	</header>

	<div class="topnav">
		<a href="userHome.jsp">Home</a>
	</div>

	<div class="h">
		<h2>Export Data</h2>
	</div>

	<section>
		<nav>
			<div class="topnav">
				<a href="allClass.jsp">View All Classrooms</a> <a
					href="updateClass.jsp">Update Classrooms</a> <a href="adjust.jsp">Adjust
					Capacity</a>
			</div>
		</nav>
		<article>

			<%if (session.getAttribute("notFound") != null){
					session.setAttribute("notFound", null); %>
			<p>
				<i>..Table not found!..</i>
			</p>
			<% } %>

			<%if (session.getAttribute("Found") != null){
					session.setAttribute("Found", null); %>
			<p>
				<i>..Table Found!..</i>
			</p>
			<% } %>

			<%if (session != null){
                                session.invalidate();} %>

			<form action="./exportServlet" method="post">
				<div class="container">
					<label for="table"><b>Table Name:</b></label> <input type="text"
						placeholder="" name="tab" required> <a href="#"
						class="btn btn-info btn-lg"> <span
						class="glyphicon glyphicon-export"></span> Export
					</a>

					<button type="submit" style="color: white">Export</button>
				</div>

				<div class="container" style="background-color: #f1f1f1">
					<button type="button" class="cancelbtn">
						<a href="userHome.jsp">Back</a>
					</button>
					</a>
				</div>
			</form>
		</article>
	</section>

	<footer> Footer </footer>

</body>

</html>