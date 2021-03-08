<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results</title>
</head>
<body>

	<c:forEach var="film" items="${films}">
			${film}
			<br>
			Cast: ${film.cast}
			<br>
			Category: ${film.category}
			<hr>
	</c:forEach>
</body>
</html>