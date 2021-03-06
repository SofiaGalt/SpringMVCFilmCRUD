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

<div>
	<h3>Search by Film ID:</h3>
	<form action="findFilmById.do" method="get">
	<label for="filmId">Film ID:</label> <input type="text" name="id">
	<input type="submit" value="Search">
	</form>
</div>

<div>
	<h3>Add new film:</h3>
	<form action=searchKeyWord.do" method="get">
	<label for="title">Title:</label> <input type="text" name="title">
	<label for="description">Description:</label> <input type="text" name="description">
	<label for=releaseYear>Release Year:</label> <input type="number" name="releaseYear">
	<label for="languageId">Language ID:</label> <input type="text" name="languageId">
	<label for="rentalDuration">Rental Duration:</label> <input type="text" name="rentalDuration">
	<label for="rentalRate">Rental Rate:</label> <input type="text" name="rentalRate">
	<label for="length">Length:</label> <input type="text" name="length">
	<label for="replacementCost">Replacement Cost:</label> <input type="text" name="replacementCost">
	<label for="rating">Rating:</label> <input type="text" name="rating">
	<label for="specialFeatures">Special Features:</label> <input type="text" name="specialFeatures">
	<input type="submit" value="Search">
	</form>
</div>



</body>
</html>