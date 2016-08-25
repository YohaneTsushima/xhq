<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div ng-app="myApp" ng-controller="memberCtrl">
	<table class="table">
		<tr ng-repeat="x in members">
			<td>{{x.memberName}}</td>
			<td>{{x.phone}}</td>
			<td>{{x.email}}</td>
		</tr>
	</table>
	<input id="d11" type="text"  onClick="WdatePicker()" readonly="readonly">
</div>