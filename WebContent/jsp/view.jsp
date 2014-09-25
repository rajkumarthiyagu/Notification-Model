
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>your ticket</title>
<link rel="stylesheet" href="normalize.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<section class="loginform cf">
<div align ="center"><h1>your ticket!!!!!!</h1></div><br>
<h3>category :<%=request.getParameter("category")%></h3><br>
<h3>sub category : <%=request.getParameter("sub")%></h3><br>
<h3>subject:<%=request.getParameter("subj")%></h3><br>
<h3>query:<%=request.getParameter("query")%></h3><br>
<h3>Ticket Id:<%=request.getParameter("event")%></h3><br>
</section>
</body>
</html>