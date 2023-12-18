<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD Users -->

<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		user</button>

	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container">
				<div class="row">
					<div class=col-md-3>Login</div>
					<div class=col-md-3>Email</div>
					<div class=col-md-3>Password</div>
					<div class=col-md-2>Role</div>
				</div>

				<form:form commandName="crud_user" action="create?user_id=0"
					method="post">
					<div class="row">
						<div class=col-md-3>
							<form:input class="form-control input-md" placeholder="login"
								path="login" required="true" />
						</div>
						<div class=col-md-3>
							<form:input class="form-control input-md" placeholder="email"
								path="email" required="true" />
						</div>
						<div class=col-md-3>
							<form:input class="form-control input-md" placeholder="password"
								path="password" required="true" type="password" />
						</div>
						<div class=col-md-2>
							<form:select class="form-control" path="role.id" required="true">
								<form:options items="${rolelist}" itemValue="id"
									itemLabel="roleName" />
							</form:select>
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
		<form:form commandName="crud_user" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_user"></div>
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
				$('#found_user').html(data.foundUser);
			}
		})
	}
</script>
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
		<form:form commandName="crud_user" method="post">
			<div class="row">
				<div class="col-md-1">
					<form:input class="form-control input-md" path="id"
						value="${user.id}" readonly="true" />
				</div>
				<div class="col-md-2">
					<form:input class="form-control input-md" placeholder="login"
						path="login" required="true" value="${user.login}" />
				</div>
				<div class="col-md-2">
					<form:input class="form-control input-md" placeholder="email"
						path="email" required="true" value="${user.email}" />
				</div>
				<div class="col-md-2">
					<form:input class="form-control input-md" placeholder="password"
						path="password" required="true" value="${user.password}" />
				</div>
				<div class=col-md-2>
					<form:select class="form-control" path="role.id" required="true">
						<c:forEach items="${rolelist}" var="role">
							<option value="${role.id}"
								${role.id==user.role.id? 'selected':''}>
								${role.roleName}</option>
						</c:forEach>
					</form:select>
				</div>
				<form:button formaction="update" class="btn btn-success">Обновить</form:button>
				<form:button formaction="delete" class="btn btn-danger">Удалить</form:button>
			</div>
		</form:form>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>