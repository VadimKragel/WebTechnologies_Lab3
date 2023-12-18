<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<div class="row">
	<div class=col-md-2>login :</div>
	<div class=col-md-2>${current_user.login}</div>
</div>
<div class="row">
	<div class=col-md-2>email :</div>
	<div class=col-md-2>${current_user.email}</div>
</div>
<div class="row">
	<div class=col-md-2>password :</div>
	<div class=col-md-2>${current_user.password}</div>
</div>

<c:if test="${current_user_current_order!=null}">
	<hr style="border: 1px solid black;">
	<div align="center">
		<b>Order № ${current_user_current_order.orderNumber}</b>
	</div>

	<c:set var="totalPrice" value="0" />
	<c:forEach items="${current_user_current_order.tickets}" var="ticket"
		varStatus="loop">

		<c:set var="filmSession" value="${ticket.filmSession}" />
		<c:set var="seat" value="${ticket.seat}" />
		<c:set var="ticketPrice" value="${filmSession.ticketPrice}" />
		<c:set var="totalPrice" value="${totalPrice+ticketPrice}" />

		<div class="row">
			<div class="col-md-0.5">${loop.count}.</div>
			<div class="row col-md-11">
				<div class="col-md-2">film name:</div>
				<div class="col-md-9">${filmSession.film.filmName}</div>
				<div class="col-md-2">date:</div>
				<div class="col-md-9">${filmSession.date}</div>
				<div class="col-md-2">time:</div>
				<div class="col-md-9">${filmSession.time}</div>
				<div class="col-md-2">seat(r/n):</div>
				<div class="col-md-9">${seat.row}/${seat.number}</div>
			</div>
		</div>
		<div align="right">price: ${ticketPrice}</div>
		<hr>
	</c:forEach>

	<div align="right" style="color: red;">
		<div class="container">
			<b>total: ${totalPrice}</b><br> <a
				href="/cinema/newapp/user/pay?current_user_current_order_id=${current_user_current_order.id}"><button
					class="btn btn-success btn-lg">Pay</button></a>
		</div>
	</div>
</c:if>

<%@ include file="../include/end-html.jsp"%>