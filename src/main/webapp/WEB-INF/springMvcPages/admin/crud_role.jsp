<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD roles -->
<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		role</button>
	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container"></div>
			<form:form commandName="crud_role" action="create?role_id=0"
				method="post">
				<div class="row">
					<div class=col-md-2>roleName :</div>
					<form:input class="col-md-9" placeholder="roleName" path="roleName"
						required="true" />
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
		<form:form commandName="crud_role" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_role"></div>
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
				$('#found_role').html(data.foundRole);
			}
		})
	}
</script>
<hr>
<div class="container">
	<c:forEach items="${rolelist}" var="role">
		<form:form commandName="crud_role" method="post">
			<div class="row">
				<form:input class="form-control input-md col-md-1" path="id"
					value="${role.id}" readonly="true" />
				<form:input class="form-control input-md col-md-6"
					placeholder="roleName" path="roleName" required="true"
					value="${role.roleName}" />
				<form:button formaction="update" class="btn btn-success">Обновить</form:button>
				<form:button formaction="delete" class="btn btn-danger">Удалить</form:button>
			</div>
		</form:form>
		<br>
	</c:forEach>
</div>

<%@ include file="../include/end-html.jsp"%>