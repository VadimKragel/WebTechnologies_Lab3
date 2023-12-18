<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Фильмы жанра "${user_chosen_genre.genreName}"</h3>
<hr>
<div class="container">
	<c:forEach items="${user_chosen_genre.films}" var="film">
		<div class="row">
			<h4>${film.filmName}</h4>
		</div>
		<div class="row">
			<div class=col-md-3>
				<a href="/cinema/newapp/user/film_page?film_id=${film.id}"><img
					src="${film.posterUrl}" width="250" height="400" /></a>
			</div>
			<div class="col-md-8 container">
				<div>
					<b>Жанры :</b><br>
					<c:forEach items="${film.genres}" var="genre">
								${genre.genreName}
						</c:forEach>
				</div>
				<br>
				<div>
					<b>Описание :</b> <br> ${film.description}
				</div>
			</div>
		</div>
		<div align="right">
			<a href="/cinema/newapp/user/filmPage?film_id=${film.id}"
				class="btn btn-success btn-lg active" role="button"
				aria-pressed="true">Details</a>
		</div>
		<hr>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>