<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="cctg"%>
<%@ include file="../include/begin-html.jsp"%>

<p>Выберите место:</p>
<div class="container" align="center" >
	<c:forEach begin="1" end="20" step="1" varStatus="row">
		<c:forEach begin="1" end="25" step="1" varStatus="column">
			<cctg:InitSeatParamTag row="${row.index}"
				column="${column.index}" filmSession="${user_chosen_filmSession}"
				isStateRequired="true" />
			<c:choose>
				<c:when test="${seat!=null}">
					<form:form method="post" commandName="user_chosen_seat"
						action="toBasket?user_chosen_filmSession_id=${user_chosen_filmSession.id}" style="display:inline-block;">
						<form:hidden path="id" value="${seat.id}" />
						<form:hidden path="row" value="${seat.row}" />
						<form:hidden path="number" value="${seat.number}" />
						<button class="btn"
							title="row:${seat.row}
number:${seat.number}
price:${user_chosen_filmSession.ticketPrice}"
							style="background-color:${seat.state.buttonColor};"></button>
					</form:form>
				</c:when>
				<c:otherwise>
					<button class="btn btn-light" disabled="disabled"></button>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<br>
	</c:forEach>
</div>
<%@ include file="../include/end-html.jsp"%>