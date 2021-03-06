<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div>
			<h3>Edit film:</h3>
			<form action="updateFilm.do" method="get">
				<label for="title">Title:</label> <input type="text" name="title" value="${film.title }">
				<label for="description">Description:</label> <input type="text"name="description" value="${film.description }">
				<label for=releaseYear>Release Year:</label> <input type="number" name="releaseYear"> 
				<label for="languageId">Language ID:</label> <input type="text"name="languageId"> 
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