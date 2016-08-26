<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">	
	<table class="table">
		<tr>
			<td>${member.memberName }</td>
			<td>${member.email }</td>
			<td>${member.phone }</td>
			<td>${member.regDate }</td>
		</tr>
	</table>
</div>