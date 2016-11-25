<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-2">
	<div class="list-group">
	  <a href="javascript:void(0)" class="list-group-item active">修改个人信息</a>
	  <a href="${pageContext.request.contextPath }/edit_usr/editImage" class="list-group-item">修改头像</a>
	  <a href="#" class="list-group-item">个人基础信息</a>
	  <a href="#" class="list-group-item">等待开发</a>
	  <a href="#" class="list-group-item">等待开发</a>
	</div>
</div>
<div class="col-md-10">
	<c:if test="${action == 'editImage' }">
		<%@ include file="change-image.jsp" %>
	</c:if>
</div>