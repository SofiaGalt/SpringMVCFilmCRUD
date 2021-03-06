<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${film}</h1>


	<!-- 	<h3>Delete film:</h3>-->
	<form action="delete" method="get">
		<label for="hidden"></label> 
		<input type="submit" value="Edit"> <input type="submit"
			value="Delete">

		<!-- <form action=searchKeyWord.do" method="get">
	<label for="title">Title:</label> <input type="text" name="title">
	<label for="description">Description:</label> <input type="text" name="description">
	<label for=releaseYear>Release Year:</label> <input type="number" name="releaseYear">
	<label for="languageId">Language ID:</label> <input type="text" name="languageId">
	<label for="rentalDuration">Rental Duration:</label> <input type="text" name="rentalDuration">
	<label for="rentalRate">Rental Rate:</label> <input type="text" name="rentalRate">
	<label for="length">Length:</label> <input type="text" name="length">
	<label for="replacementCost">Replacement Cost:</label> <input type="text" name="replacementCost">
	<label for="rating">Rating:</label> <input type="text" name="rating">
	<label for="specialFeatures">Special Features:</label> <input type="text" name="specialFeatures"> -->
</body>
</html>