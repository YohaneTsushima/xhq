<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	var app = angular.module('myApp', []);
	var url="";
	app.controller('memberCtrl', function($scope, $http){
		url = "${pageContext.request.contextPath }/member";
		$http.get(url)
		.success(function(response){
			$scope.members = response.members;
			$scope.page = response.page.slide;
			$scope.pageNo = response.page.pageNo;
			$scope.pageSize = response.page.pageSize;
			$scope.current = response.current;
		}).error(function(error){
			alert(error);
		});
		$scope.getMember = function(pageNo){
			url = "${pageContext.request.contextPath }/member?pageNo="+pageNo;
			$http.get(url)
			.success(function(response){
				$scope.members = response.members;
				$scope.pageNo = response.page.pageNo;
				$scope.pageSize = response.page.pageSize;
				$scope.page = response.page.slide;
				$scope.current = response.current;
			}).error(function(error){
				alert(error);
			});
		}
		
	});
</script>
<div ng-app="myApp" ng-controller="memberCtrl">
	<table class="table">
		<tr ng-repeat="x in members">
			<td><a href="${pageContext.request.contextPath }/detail?id={{x.id}}">{{x.memberName}}</a></td>
			<td>{{x.regDate}}</td>
			<td>{{x.phone}}</td>
			<td>{{x.email}}</td>
			<td>{{x.id}}</td>
		</tr>
	</table>
	<div class="page">
		<ul class="pagination">
			<li><a ng-click="getMember(pageNo-pageSize)" href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			<li ng-repeat="p in page"><a ng-click="getMember(p)" href="javascript:void(0);">{{p}}</a></li>
			<li><a ng-click="getMember(pageNo+pageSize)" href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</div>
	<input id="d11" type="text"  onClick="WdatePicker()" readonly="readonly">
	<div class="msg">
	
	</div>
</div>