<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Blog</title>
<meta charset="ISO-8859-1">
<title>Blog</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


	<div class="container" id="contenedor-principal">
		<h2>Posts</h2>

		<c:forEach var="post" items="${sessionScope.posts}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">${post.value}</h3>
				</div>
				<div class="panel-body">${ post.key}</div>
			</div>
		</c:forEach>
	</div>
	<div class="container" id="contenedor-opciones">
		<h2>Añadir comentario</h2>
		<form action="/sdi1102-lab-jee/blog" method="post">
			<div class="form-group" method="post" action="/blog">
				<label for=user>Username</label>
				<input type="text"
					class="form-control" id="user" name="user"
					placeholder="enter your username"> <label for="post">Post</label>
				<input type="text" class="form-control" id="post" name="post"
					placeholder="enter your post here">

				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>

	</div>

</body>
</html>