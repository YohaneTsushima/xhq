<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="../default/header.jsp" %>
	<body>
		<script type="text/javascript">
			var app = angular.module('users', []);
			var url = "";
			var page_no = "";
			app.controller("getUsers", function($scope, $http) {
				url = "/JFinal/user/show";
				$http.get(url)
				.success(function(data, status, headers, config) {
					debugger;
					$scope.users = data.users;
					$scope.pageNo = data.pager.pageNo;
					$scope.pageSize = data.pager.pageSize;
					$scope.page = data.pager.slide;
					$scope.total = data.pager.totalPage;
				})
				.error(function(data, status, headers, config) {
					alert(data);
				});
				
				$scope.getUsers = function(pageNo){
					page_no = pageNo;
					url = "/JFinal/user/show?pageNo=" + page_no;
					$http.get(url)
					.success(function(data, status, headers, config) {
						$scope.users = data.users;
						$scope.pageNo = data.pager.pageNo;
						$scope.pageSize = data.pager.pageSize;
						$scope.page = data.pager.slide;
						$scope.total = data.pager.totalPage;
					})
					.error(function(data, status, headers, config) {
						alert(data);
					});
				}
			});
		</script>
		<%@ include file="../default/nav.jsp" %>
		<div class="container" ng-app="users" ng-controller="getUsers">
			<div style="height: 220px;">
				<table class="table">
					<tr>
						<td width="5%">编号</td>
						<td width="15%">登录名</td>
						<td width="20%">注册日期</td>
						<td>电话</td>
						<td>邮箱</td>
						<td>住址</td>
						<td>状态</td>
					</tr>
					<tr ng-repeat="u in users">
						<td>{{u.id}}</td>
						<td>{{u.login_name}}</td>
						<td>{{u.regDate}}</td>
						<td>{{u.phone}}</td>
						<td>{{u.email}}</td>
						<td>{{u.address}}</td>
						<td>{{u.isActivited}}</td>
					</tr>
				</table>
			</div>
			<div class="page">
				<ul class="pagination">				
					<li><a ng-click="getUsers(pageNo-1)" href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>				
					<li ng-repeat="p in page"><a ng-click="getUsers(p)" href="javascript:void(0);">{{p}}</a></li>				
					<li><a class="" ng-click="getUsers(pageNo+1)" href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>				
				</ul>
			</div>
		</div>
	</body>
</html>