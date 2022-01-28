<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Note</title>
</head>
<body>
	<form action="CreateServlet" method="post">
		Namee: <input type="text" name="userName">
		Title: <input type="text" name="title">
		Details: <input type="text" name="description">
		<input type="submit" value="Call Servlet"/>
	</form>
</body>
</html>