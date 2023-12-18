<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>


<%-- <div class="row">
	<img src="${film.posterUrl}" />
	<div class="container">
		${film.filmName} <br> ${film.description}
	</div>
</div> --%>

<div class="container">
	<c:forEach items="${filmlist}" var="film">

		<div class="row">
			<h2>${film.filmName}</h2>
		</div>
		<div class="row">
			<div class=col-md-3>
				<a href="oldapp?action=view_film_page&film_id=${film.id}"><img
					src="${film.posterUrl}" /></a>
			</div>
			<div class="col-md-8 container">
				<p>
					Жанры :
					<c:forEach items="${film.genres}" var="genre">
								${genre.genreName}
						</c:forEach>
				</p>
				<p>
					Описание : <br> ${film.description}
				</p>
			</div>
		</div>
		<hr>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>