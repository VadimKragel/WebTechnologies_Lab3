<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD genres -->
<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		genre</button>
	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container"></div>
			<form:form commandName="crud_genre" action="create?genre_id=0"
				method="post">
				<div class="row">
					<div class=col-md-2>genreName :</div>
					<form:input class="col-md-9" placeholder="genreName"
						path="genreName" required="true" />
				</div>
				<button class="btn btn-success">ok</button>
			</form:form>
		</div>
	</div>
</div>
<hr>
<div class="container">
	<p>Введите id для поиска</p>
	<div class="row">
		<form:form commandName="crud_genre" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_genre"></div>
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
				$('#found_genre').html(data.foundGenre);
			}
		})
	}
</script>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-2>Genre</div>
	</div>
</div>
<br>
<div class="container">
	<c:forEach items="${genrelist}" var="genre">
		<form:form commandName="crud_genre" method="post">
			<div class="row">
				<form:input class="form-control input-md col-md-1" path="id"
					value="${genre.id}" readonly="true" />
				<form:input class="form-control input-md col-md-6"
					placeholder="genreName" path="genreName" required="true"
					value="${genre.genreName}" />
				<form:button formaction="update" class="btn btn-success">Обновить</form:button>
				<form:button formaction="delete" class="btn btn-danger">Удалить</form:button>
			</div>
		</form:form>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>