<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1><c:out value="Welcome, ${ user_info.username } !"></c:out></h1>

<form action="disconnect" method="post">
	<input type="submit" name="disconnect" value="déconnectez-vous">
</form>
</body>
</html>