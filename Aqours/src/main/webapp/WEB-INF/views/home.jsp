<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div ng-app="myApp" ng-controller="memberCtrl">
	<table class="table">
		<tr ng-repeat="x in members">
			<td>{{x.memberName}}</td>
			<td>{{x.regDate}}</td>
			<td>{{x.phone}}</td>
			<td>{{x.email}}</td>
		</tr>
	</table>
	<input id="d11" type="text"  onClick="WdatePicker()" readonly="readonly">
</div>