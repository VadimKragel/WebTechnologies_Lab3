<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD Roles -->

<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create</button>

	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container">
				<div class="row">
					<div class=col-md-2>FilmName</div>
					<div class=col-md-6>Description</div>
					<div class=col-md-1>PosterUrl</div>
					<div class=col-md-2>Genres</div>
				</div>
			</div>

			<form class="create-film" action="oldapp?action=crud_film&film_id=0"
				method=POST>
				<div class="row">
					<div class=col-md-2>
						<input id="film_name" class="form-control input-md"
							name="film_name" />
					</div>
					<div class=col-md-6>
						<input id="film_description" class="form-control input-md"
							name="film_description" />
					</div>
					<div class=col-md-1>
						<input id="film_poster_url" class="form-control input-md"
							name="film_poster_url" />
					</div>
					<div class=col-md-2>
						<select id="genre" class="form-control" name="film_genres"
							multiple="multiple" size="5" required>
							<option disabled>Выберите жанр</option>
							<c:forEach items="${genrelist}" var="genre">
								<option value="${genre.id}">${genre.genreName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button id="create" value="create" name="crud_command"
					class="btn btn-success">ok</button>
			</form>
		</div>
	</div>
</div>
<hr>
<div class="container">
	<p>Введите id для поиска</p>
	<form class="read-film" action="oldapp?action=crud_film" method=POST>
		<div class="row">
			<div class=col-md-2>
				<input id="film_id" class="form-control input-md" name="film_id" />
			</div>
			<button id="read" value="read" name="crud_command"
				class="btn btn-success">search</button>
		</div>
		<p>Результат поиска:</p>
		<p>${found_film}</p>
	</form>
</div>
<hr>
<br>
<div class="container">
	<c:forEach items="${filmlist}" var="film">

		<form class="update-user"
			action="oldapp?action=crud_film&film_id=${film.id}" method=POST>

			<div class="row">
				<div class=col-md-2>ID :</div>
				<div class=col-md-10>${film.id}</div>
			</div>
			<div class="row">
				<div class=col-md-2>FilmName :</div>
				<div class=col-md-10>
					<input id="film_name" class="form-control input-md"
						name="film_name" value="${film.filmName}" />
				</div>
			</div>
			<div class="row">
				<div class=col-md-2>PosterUrl :</div>
				<div class=col-md-10>
					<input id="film_poster_url" class="form-control input-md"
						name="film_poster_url" value="${film.posterUrl}" />
				</div>
			</div>

			<div class="row">
				<div class=col-md-2>Description :</div>
				<div class=col-md-8>
					<textarea id="film_description" name="film_description" cols="100"
						rows="7">${film.description} </textarea>
				</div>
				<div class=col-md-1.5>
					<p>Genres :</p>
					<select id="genre" class="form-control" name="film_genres"
						multiple="multiple" size="5" required>
						<option disabled>Выберите жанр</option>
						<c:forEach items="${genrelist}" var="genre">
							<option value="${genre.id}"
								${film.genres.contains(genre)?"selected":""}>
								${genre.genreName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<button id="update" value="update" name="crud_command"
				class="btn btn-success">Обновить</button>

			<button id="delete" value="delete" name="crud_command"
				class="btn btn-danger">Удалить</button>
		</form>
		<br>
	</c:forEach>
</div>


<%@ include file="../include/end-html.jsp"%>