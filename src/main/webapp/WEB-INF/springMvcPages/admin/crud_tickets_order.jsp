<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD TicketsOrder -->

<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		ticketsOrder</button>

	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container">
				<div class="row">
					<div class=col-md-2>user</div>
					<div class=col-md-3>isPaid</div>
				</div>

				<form:form commandName="crud_tickets_order" action="create"
					method="post">
					<div class="row">
						<div class=col-md-2>
							<form:select class="form-control" path="user.id" required="true">
								<form:options items="${userlist}" itemValue="id"
									itemLabel="login" />
							</form:select>
						</div>
						<div class=col-md-3>
							<form:select class="form-control" path="isPaid" required="true">
								<option value="true" label="YES" />
								<option value="false" label="NO" selected />
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
		<form:form commandName="crud_tickets_order" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_tickets_order"></div>
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
				$('#found_tickets_order').html(data.foundTicketsOrder);
			}
		})
	}
</script>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-2>orderNumber</div>
		<div class=col-md-2>user</div>
		<div class=col-md-3>isPaid</div>
	</div>
</div>
<div class="container">
	<c:forEach items="${ticketsOrderList}" var="order">
		<form:form commandName="crud_tickets_order" method="post">
			<div class="row">
				<div class="col-md-1">
					<form:input class="form-control input-md" path="id"
						value="${order.id}" readonly="true" />
				</div>
				<div class="col-md-2">
					<form:input class="form-control input-md" path="orderNumber"
						value="${order.orderNumber}" />
				</div>
				<div class="col-md-2">
					<form:select class="form-control" path="user.id" required="true">
						<c:forEach items="${userlist}" var="user">
							<option value="${user.id}"
								${user.id==order.user.id? 'selected':''}>${user.login}</option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-3">
					<form:select class="form-control" path="isPaid" required="true">
						<option value="true" label="YES" ${order.isPaid?'selected':''} />
						<option value="false" label="NO" ${!order.isPaid?'selected':''} />
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