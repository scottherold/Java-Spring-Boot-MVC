<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Information about Book <c:out value="${book.id}"/></title>
	</head>
	<body>
		<h1><c:out value="${book.title}"/></h1>
		<p>Description: <c:out value="${book.description}"/></p>
		<p>Language: <c:out value="${book.language}"/></p>
		<p>Number of pages: <c:out value="${book.numberOfPages}"/></p>
		<a href="/books/${book.id}/edit">Edit Book</a>
		<form action="/books/${book.id}" method="post">
		    <input type="hidden" name="_method" value="delete">
		    <input type="submit" value="Delete">
		</form>
	</body>
</html>