<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath }/js/angular.js"></script>
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
		<title>hello</title>
	</head>
	<body>
		<div class="container">
			<div ng-app="myApp" ng-controller="memberCtrl">
				<table class="table">
					<tr ng-repeat="x in members">
						<td>{{x.memberName}}</td>
						<td>{{x.phone}}</td>
						<td>{{x.email}}</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>