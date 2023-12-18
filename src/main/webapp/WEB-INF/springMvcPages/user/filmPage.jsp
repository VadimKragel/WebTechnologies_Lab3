<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<h4>${user_chosen_film.filmName}</h4>
</div>
<div class="row">
	<div class=col-md-3>
		<img src="${user_chosen_film.posterUrl}" width="250" height="400" />
	</div>
	<div class="col-md-8 container">
		<div>
			<b>Жанры :</b><br>
			<c:forEach items="${user_chosen_film.genres}" var="genre">
								${genre.genreName}
						</c:forEach>
		</div>
		<br>
		<div>
			<b>Описание :</b> <br> ${user_chosen_film.description}
		</div>
		<br>
		<div>
			<b>Выберите дату и время:</b> <br>
			<c:forEach items="${user_chosen_film.filmSessions}" var="session">
				<a
					href="/cinema/newapp/user/chooseSeat?user_chosen_filmSession_id=${session.id}"
					class="btn btn-success btn-lg active" role="button"
					aria-pressed="true">${session.date} ${session.time}</a>
				<br>
			</c:forEach>
		</div>
	</div>
</div>

<%@ include file="../include/end-html.jsp"%>