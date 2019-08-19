<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ninja Gold Game</title>
</head>
<body>
<h1>Your Gold: <c:out value="${gold}"/></h1>
<h1>Farm</h1>
<p>Earn 10-20 gold</p><br>
<form action="/play" method="post"><input type="submit" value="Find Gold!"><input type="hidden" name="task" value="farm"></form><br>
<h1>Casino</h1>
<p>Earn/Lose 0-50 gold</p><br>
<form action="/play" method="post"><input type="submit" value="Find Gold!"><input type="hidden" name="task" value="casino"></form><br>
<p><c:forEach var="temp" items="${logs}">
	<c:out value="${temp}"></c:out><br>
	</c:forEach>
	</p>
</body>
</html>