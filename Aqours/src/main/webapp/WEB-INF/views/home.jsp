<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var app = angular.module('myApp', []);
	app.controller('memberCtrl', function($scope, $http){
		$http.get("${pageContext.request.contextPath }/member")
			.success(function(response){
				$scope.members = response.members;
			}).error(function(error){
				alert(error);
			});
	});
</script>
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