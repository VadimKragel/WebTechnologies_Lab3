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
					<div class=col-md-2>Login</div>
					<div class=col-md-2>Email</div>
					<div class=col-md-2>Password</div>
					<div class=col-md-2>Role</div>
				</div>
			</div>

			<form class="create-user" action="oldapp?action=crud_user&user_id=0"
				method=POST>
				<div class="row">
					<div class=col-md-2>
						<input id="user_login" class="form-control input-md"
							name="user_login" />
					</div>
					<div class=col-md-2>
						<input id="user_email" class="form-control input-md"
							name="user_email" />
					</div>
					<div class=col-md-2>
						<input id="user_password" class="form-control input-md"
							name="user_password" />
					</div>

					<div class=col-md-2>
						<select id="role" class="form-control" name="role_id">
							<c:forEach items="${rolelist}" var="role">
								<option value="${role.id}">${role.roleName}</option>
							</c:forEach>
						</select>
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
	<form class="read-user" action="oldapp?action=crud_user" method=POST>
		<div class="row">
			<div class=col-md-2>
				<input id="user_id" class="form-control input-md" name="user_id" />
			</div>
			<button id="read" value="read" name="crud_command"
				class="btn btn-success">search</button>
		</div>
		<p>Результат поиска:</p>
		<p>${found_user}</p>
	</form>
</div>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-2>Login</div>
		<div class=col-md-2>Email</div>
		<div class=col-md-2>Password</div>
		<div class=col-md-2>Role</div>
	</div>
</div>

<div class="container">
	<c:forEach items="${userlist}" var="user">

		<form class="update-user"
			action="oldapp?action=crud_user&user_id=${user.id}" method=POST>
			<div class="row">
				<div class=col-md-1>${user.id}</div>
				<div class=col-md-2>
					<input id="user_login" class="form-control input-md"
						name="user_login" value="${user.login}" />
				</div>
				<div class=col-md-2>
					<input id="user_email" class="form-control input-md"
						name="user_email" value="${user.email}" />
				</div>
				<div class=col-md-2>
					<input id="user_password" class="form-control input-md"
						name="user_password" value="${user.password}" />
				</div>
				<div class=col-md-2>
					<select id="role" class="form-control" name="role_id">
						<c:forEach items="${rolelist}" var="role">
							<option value="${role.id}" ${role.id==user.role.id?"selected":""}>
								${role.roleName}</option>
						</c:forEach>
					</select>
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