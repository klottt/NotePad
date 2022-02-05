<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> User Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/NoteServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${Note != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${Note == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${Note != null}">
						Edit User
						</c:if>
						<c:if test="${Note == null}">
						Add New User
						</c:if>
					</h2>
				</caption>
				<c:if test="${note != null}">
					<input type="hidden" name="oriUser" value="<c:out value='${Note.user}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User</label> <input type="text" readonly	value="<c:out value='${Note.title}' />" class="form-control"
						name="user">
				</fieldset>
				<fieldset class="form-group">
					<label>Title</label> <input type="text" value="<c:out value='${Note.user}' />" class="form-control"
						name="title">
				</fieldset>
				<fieldset class="form-group">
					<label>Details</label> <input type="text" value="<c:out value='${Note.details}' />" class="form-control"
						name="details">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>