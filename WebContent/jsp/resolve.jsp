<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Cancel form</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<section class="loginform cf">
		<form action="resolve.html" method="get">
			Employee id&nbsp;&nbsp;&nbsp;<input type="text" name="txt_empId"
				value="<%=request.getParameter("empid")%>" /><br><br>
				Event ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="txt_eventId"
				value="<%=request.getParameter("eventId")%>" /><br>
			<br>Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="txt_category"
				value="<%=request.getParameter("category")%>" /><br> <br>
				Group ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
				name="txt_groupid"  value ="<%=request.getParameter("groupId") %>" /> <input type="submit" name="sub"
				value="Click to Resolve" />
		</form>
	</section>
</body>
</html>