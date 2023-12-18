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
					<div class=col-md-2>Роль</div>
				</div>
			</div>

			<form class="create-role" action="oldapp?action=crud_role&role_id=0"
				method=POST>
				<div class="row">
					<div class=col-md-1>${role.id}</div>
					<div class=col-md-3>
						<input id="role_name" class="form-control input-md"
							name="role_name" />
					</div>

					<button id="create" value="create" name="crud_command"
						class="btn btn-success">ok</button>
				</div>
			</form>
		</div>
	</div>
</div>
<hr>
<div class="container">
	<p>Введите id для поиска</p>
	<form class="read-role" action="oldapp?action=crud_role" method=POST>
		<div class="row">
			<div class=col-md-2>
				<input id="role_id" class="form-control input-md" name="role_id" />
			</div>
			<button id="read" value="read" name="crud_command"
				class="btn btn-success">search</button>
		</div>
		<p>Результат поиска:</p>
		<p>${found_role}</p>
	</form>
</div>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-2>Роль</div>
	</div>
</div>

<div class="container">
	<c:forEach items="${rolelist}" var="role">

		<form class="update-role"
			action="oldapp?action=crud_role&role_id=${role.id}" method=POST>
			<div class="row">
				<div class=col-md-1>${role.id}</div>
				<div class=col-md-3>
					<input id="role_name" class="form-control input-md"
						name="role_name" value="${role.roleName}" />
				</div>

				<button id="update" value="update" name="crud_command"
					class="btn btn-success">Обновить</button>

				<button id="delete" value="delete" name="crud_command"
					class="btn btn-danger">Удалить</button>
			</div>
		</form>
		<br>
	</c:forEach>

</div>


<%@ include file="../include/end-html.jsp"%>