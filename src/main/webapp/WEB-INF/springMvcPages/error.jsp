<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<div class="alert alert-danger" role="alert">
	<c:choose>
		<c:when test="${not empty param.error}">
			<span style="color: red;"> Login error. <br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</span>
		</c:when>
		<c:otherwise>
			<p>${error_message}</p>
		</c:otherwise>
	</c:choose>
</div>


<%@ include file="include/end-html.jsp"%>