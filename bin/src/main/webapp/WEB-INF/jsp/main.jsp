
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!DOCTYPE html>

<html>

<head>

<meta charset="EUC-KR">

<title>Insert title hersdfe</title>

</head>

<body>

	<h1>Main Page!</h1>
	<div>
		<a href = "<c:url value="/coffee/coffee.page"/>">coffee</a>
	</div>
	<div>
		<a href = "<c:url value="/book/book.page"/>">book</a>
	</div>
	<div>
		<a href = "logout">logout</a>
	</div>
</body>

</html>

