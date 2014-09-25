<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
<title>return</title>
</head>
<body>
	<section class="loginform cf">
		<form action="return.html" method="get">
			Employee ID&nbsp;&nbsp;<input type="text" name="txt_empId"
				value="<%=request.getParameter("empid") %>" /><br>
			<br>  category&nbsp;&nbsp;&nbsp;<input type="text" name="txt_category"
				value="<%=request.getParameter("category") %>" /><br><br>Group Id&nbsp;&nbsp;&nbsp;<input type="text"
				name="txt_groupid"  value ="<%=request.getParameter("groupId") %>" /> <input type="submit" name="sub"
				value="Click to return Ticket" />
		</form>
	</section>
</body>
</html>