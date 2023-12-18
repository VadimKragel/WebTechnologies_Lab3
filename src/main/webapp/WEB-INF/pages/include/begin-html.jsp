<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<title>Cinema</title>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href=".">Home</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<c:if test="${current_user.role.id==1}">
				<!-- <div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">I/R DataBase</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="do?action=InitDB">InitDB</a> <a
						class="dropdown-item" href="do?action=ResetDB">ResetDB</a>
				</div>
			</div> -->

				<div class="dropdown">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">DataBase
						actions</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="oldapp?action=crud_role">CRUD
							role</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_user">CRUD
							user</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_order">CRUD
							order</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_ticket">CRUD
							ticket</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_seat">CRUD
							seat</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_session">CRUD
							session</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_film">CRUD
							film</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="oldapp?action=crud_genre">CRUD
							genre</a>
					</div>
				</div>
			</c:if>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">


					<!-- <li class="nav-item active"><a class="nav-link"
						href="do?action=Catalog">Catalog<span class="sr-only">(current)</span></a>
					</li> -->


				</ul>
			</div>


			<ul class="navbar-nav navbar-right">
				<c:choose>
					<c:when test="${current_user!=null}">
						<li class="nav-item active"><a class="nav-link"
							href="profile" style="color: #FF0000"> <b>${current_user.login}</b>
								<span class="sr-only">(current)</span></a></li>
						<li class="nav-item active"><a class="nav-link" href="logout">Logout
								<span class="sr-only">(current)</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item active"><a class="nav-link" href="login">Login
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link" href="signUp">SignUp
								<span class="sr-only">(current)</span>
						</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>