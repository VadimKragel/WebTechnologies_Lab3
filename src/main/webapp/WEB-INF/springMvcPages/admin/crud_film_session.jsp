<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD FilmSession -->

<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		FilmSession</button>

	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container">
				<div class="row">
					<div class=col-md-3>Film</div>
					<div class=col-md-3>Date</div>
					<div class=col-md-2>Time</div>
					<div class=col-md-2>TicketPrice</div>
				</div>

				<form:form commandName="crud_film_session"
					action="create?film_session_id=0" method="post">
					<div class="row">
						<div class=col-md-3>
							<form:select class="form-control" path="film.id" required="true">
								<form:options items="${filmlist}" itemValue="id"
									itemLabel="filmName" />
							</form:select>
						</div>
						<div class=col-md-3>
							<form:input class="form-control input-md" type="date" path="date"
								required="true" />
						</div>
						<div class=col-md-2>
							<form:input class="form-control input-md" type="time" path="time"
								required="true" />
						</div>
						<div class=col-md-2>
							<form:input class="form-control input-md" placeholder="price"
								path="ticketPrice" required="true" />
						</div>

						<button class="btn btn-success">ok</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<hr>
<div class="container">
	<p>Введите id для поиска</p>
	<div class="row">
		<form:form commandName="crud_film_session" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_film_session"></div>
</div>
<script type="text/javascript">
	function read() {
		$.ajax({
			url : 'read',
			data : ({
				id : $('#id').val()
			}),
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			success : function(data) {
				$('#found_film_session').html(data.foundFilmSession);
			}
		})
	}
</script>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-2>Film</div>
		<div class=col-md-3>Date</div>
		<div class=col-md-2>Time</div>
		<div class=col-md-1>TicketPrice</div>
	</div>
</div>
<div class="container">
	<c:forEach items="${filmSessionlist}" var="filmSession">
		<form:form commandName="crud_film_session" method="post">
			<div class="row">
				<div class="col-md-1">
					<form:input class="form-control input-md" path="id"
						value="${filmSession.id}" readonly="true" />
				</div>
				<div class=col-md-2>
					<form:select class="form-control" path="film.id" required="true">
						<c:forEach items="${filmlist}" var="film">
							<option value="${film.id}"
								${film.id==filmSession.film.id? 'selected':''}>
								${film.filmName}</option>
						</c:forEach>
					</form:select>
				</div>
				<div class=col-md-3>
					<form:input class="form-control input-md" type="date" path="date"
						required="true" value="${filmSession.date}" />
				</div>
				<div class=col-md-2>
					<form:input class="form-control input-md" type="time" path="time"
						required="true" value="${filmSession.time}" />
				</div>
				<div class="col-md-1">
					<form:input class="form-control input-md" placeholder="price"
						path="ticketPrice" required="true"
						value="${filmSession.ticketPrice}" />
				</div>

				<form:button formaction="update" class="btn btn-success">Обновить</form:button>
				<form:button formaction="delete" class="btn btn-danger">Удалить</form:button>
			</div>
		</form:form>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>