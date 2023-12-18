<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../include/begin-html.jsp"%>

<!-- CRUD Ticket -->

<div class="container">
	<button class="btn btn-success btn-lg" type="button"
		data-toggle="collapse" data-target="#collapseExample"
		aria-expanded="false" aria-controls="collapseExample">Create
		ticket</button>

	<div class="collapse" id="collapseExample">
		<div class="card card-body">
			<div class="container">
				<div class="row">
					<div class=col-md-2>SessionId</div>
					<div class=col-md-2>SeatId</div>
					<div class=col-md-3>Order</div>
				</div>

				<form:form commandName="crud_ticket" action="create" method="post">
					<div class="row">
						<div class=col-md-2>
							<form:select class="form-control" path="filmSession.id"
								required="true">
								<form:options items="${filmSessionlist}" itemValue="id"
									itemLabel="id" />
							</form:select>
						</div>
						<div class=col-md-2>
							<form:select class="form-control" path="seat.id" required="true">
								<form:options items="${seatlist}" itemValue="id" itemLabel='id' />
							</form:select>
						</div>
						<div class=col-md-3>
							<form:select class="form-control" path="order.id" required="true">
								<form:options items="${ticketsOrderList}" itemValue="id"
									itemLabel="orderNumber" />
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
		<form:form commandName="crud_ticket" action="read" method="get">
			<fieldset>
				<form:input class="form-control input-md" path="id" />
			</fieldset>
		</form:form>
		<button class="btn btn-success" onclick="read()">search</button>
	</div>
	<p>Результат поиска:</p>
	<div id="found_ticket"></div>
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
				$('#found_ticket').html(data.foundTicket);
			}
		})
	}
</script>
<hr>
<div class="container">
	<div class="row">
		<div class=col-md-1>ID</div>
		<div class=col-md-3>Session</div>
		<div class=col-md-1>Seat(r/n)</div>
		<div class=col-md-3>Order</div>
	</div>
</div>
<div class="container">
	<c:forEach items="${ticketList}" var="ticket">
		<form:form commandName="crud_ticket" method="post">
			<div class="row">
				<div class="col-md-1">
					<form:input class="form-control input-md" path="id"
						value="${ticket.id}" readonly="true" />
				</div>
				<div class="col-md-3">
					<form:select class="form-control" path="filmSession.id"
						required="true">
						<c:forEach items="${filmSessionlist}" var="session">
							<option value="${session.id}"
								${session.id==ticket.filmSession.id? 'selected':''}>
								<c:out
									value="${session.film.filmName} ${session.date} ${session.time}" /></option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-1.5">
					<form:select class="form-control" path="seat.id"
						required="true">
						<c:forEach items="${seatlist}" var="seat">
							<option value="${seat.id}"
								${seat.id==ticket.seat.id? 'selected':''}>
								<c:out value="${seat.row}/${seat.number}" /></option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-md-3">
					<form:select class="form-control" path="order.id"
						required="true">
						<c:forEach items="${ticketsOrderList}" var="order">
							<option value="${order.id}"
								${order.id==ticket.order.id? 'selected':''}>
								<c:out value="id:${order.id} login:${order.user.login}" /></option>
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