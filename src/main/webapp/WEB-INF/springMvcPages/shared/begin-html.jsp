<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="cctg" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cinema</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/cinema/newapp/user/">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button"
                                id="dropdownMenuButton" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">DataBase
                            actions
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/role/">CRUD
                                role</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/user/">CRUD
                                user</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/order/">CRUD
                                order</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="/cinema/newapp/admin/crud/ticket/">CRUD ticket</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/seat/">CRUD
                                seat</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="/cinema/newapp/admin/crud/session/">CRUD session</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/film/">CRUD
                                film</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/cinema/newapp/admin/crud/genre/">CRUD
                                genre</a>
                        </div>
                    </div>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav mx-auto">
                <li id="timer" style="color: #FF0000; font-size: 30px;"/>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item active"><a class="nav-link"
                                                   href="/cinema/newapp/user/profile"
                                                   style="color: #FF0000"><b><sec:authentication
                            property="principal.username"/></b></a></li>
                    <li class="nav-item active"><a class="nav-link"
                                                   href="<c:url value='/j_spring_security_logout' />">Logout</a></li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item active"><a class="nav-link"
                                                   href="/cinema/newapp/user/login">Login</a></li>
                    <li class="nav-item active"><a class="nav-link"
                                                   href="/cinema/newapp/user/sign_up">SignUp</a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
    <div class="row">
        <div class="col-md-2">
            <br>
            <cctg:DisplayGenresBlock/>
        </div>
        <div class="col-md-10">