<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Film Site</title>
</head>
<body>
<h1>My Film Site</h1>

<!-- Find by Last Name -->
<div>
	<h3>Search by Film ID:</h3>
	<form action="findFilmById.do" method="get">
	<label for="filmId">Film ID:</label> <input type="text" name="id">
	<input type="submit" value="Search">
	</form>
</div>
<!-- Find by Party -->
<div>
	<h3>Search by party:</h3>
	<form action="presidentInfo" method="get">
	<label for="party">Party:</label> <input type="text" name="party">
	<input type="submit" value="Search">
	</form>
</div>

</body>
</html>