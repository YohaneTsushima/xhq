<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="../default/header.jsp" %>
<body>
	<%@include file="../default/nav.jsp" %>
	<div class="container">	
		<div class="row" style="height: 800px;">
			<%@ include file="menu.jsp" %>
			<div class="col-md-10">
				<label class="rupdate_esult">${msg }</label>
				<%@ include file="user-info.jsp" %>
			</div>
		</div>	
		<a href="${pageContext.request.contextPath }/user/">返回</a>
	</div>
</body>
</html>