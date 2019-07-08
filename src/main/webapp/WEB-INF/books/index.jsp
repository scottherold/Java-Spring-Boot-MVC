<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>All Books</title>
	</head>
	<body>
		<h1>All Books</h1>
		<table>
		    <thead>
		        <tr>
		            <th>Title</th>
		            <th>Description</th>
		            <th>Language</th>
		            <th>Number of Pages</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${books}" var="book">
		        <tr>
		            <td><a href="/books/<c:out value="${book.id}"/>"><c:out value="${book.title}"/></a></td>
		            <td><c:out value="${book.description}"/></td>
		            <td><c:out value="${book.language}"/></td>
		            <td><c:out value="${book.numberOfPages}"/></td>
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		<a href="/books/new">New Book</a>
	</body>
</html>