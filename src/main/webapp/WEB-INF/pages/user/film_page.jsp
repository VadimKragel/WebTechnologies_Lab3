<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<div class="row">
	<h2>${user_chosen_film.filmName}</h2>
</div>
<div class="row">
	<div class=col-md-3>
		<img src="${user_chosen_film.posterUrl}" />
	</div>
	<div class="col-md-8 container">
		<p>
			Жанры :
			<c:forEach items="${user_chosen_film.genres}" var="genre">
								${genre.genreName}
						</c:forEach>
		</p>
		<p>
			Описание : <br> ${user_chosen_film.description}
		</p>
		<form class="update-genre"
			action="oldapp?action=view_film_page&film_id=${user_chosen_film.id}"
			method=POST>
			<%-- <p>Выберите дату:</p>
			<input type="date" name="user_chosen_date" min="${current_date}">
			<button>ok</button> --%>
			<c:if test="${not empty chosen_film_film_sessions}">
				<div class="container">
					<div class="row">
						<div class=col-md-3>Дата</div>
						<div class=col-md-3>Время</div>
					</div>
					<hr>
					<c:forEach items="${user_chosen_film.filmSessions}" var="session">
						<div class="row">
							<div class=col-md-3>${session.date}</div>
							<div class=col-md-3>${session.time}</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</form>
	</div>
</div>

<%@ include file="../include/end-html.jsp"%>