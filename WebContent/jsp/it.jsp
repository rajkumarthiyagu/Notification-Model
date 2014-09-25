
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>


<title>INFORMATION TECHNOLOGY</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>

<body>

	<section class="loginform cf">
		<form action="itform.html" method="post">
			enter the empid<input type = "text" name="txt-viewEmp">
			Click here to see Notifications <input type="submit" name="sub"
				value="Click me" />
		</form>
	</section>
	<form action="" method="get">

		<c:forEach var="query" items="${form}">

			<p>${query.empId}- ${query.category}- ${query.subject}-${query.eventId}</p>
			<a href="return.jsp?empid=${query.empId}&category=${query.category}&eventid=${query.eventId}&groupId=${query.groupId}">return</a>
			<br>
			<a href="cancel.jsp?empid=${query.empId}&eventId=${query.eventId}&category=${query.category}&groupId=${query.groupId}">Cancel</a>
			<br>
			<a
				href="Assignto.jsp?empid=${query.empId}&category=${query.category}&eventid=${query.eventId}&groupId=${query.groupId}">Assign
				to</a><br>
				<a href="resolve.jsp?empid=${query.empId}&eventId=${query.eventId}&category=${query.category}&groupId=${query.groupId}">Resolve</a>
			<br>
		</c:forEach>
	</form>
</body>
</html>