<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="default/header.jsp" %>
<body>
	<script type="text/javascript">
		var app = angular.module('members', []);
		var url = "";
		var pageNo_ = "";
		app.controller("getMembers", function($scope, $http) {
			url = "/JFinal/getMembers";
			$http.get(url)
			.success(function(response) {
				debugger;
				$scope.members = response.members;
				$scope.pageNo = response.pager.pageNo;
				$scope.pageSize = response.pager.pageSize;
				$scope.page = response.pager.slide;
				$scope.total = response.pager.totalPage;
				$scope.current = response.current;
			})
			.error(function(error){
				alert(error);
			});
						
			$scope.getMembers = function(pageNo){
				pageNo_ = pageNo;
				url = "/JFinal/getMembers?pageNo=" + pageNo;
				$http.get(url)
				.success(function(response){
					debugger;
					$scope.members = response.members;
					$scope.pageNo = response.pager.pageNo;
					$scope.pageSize = response.pager.pageSize;
					$scope.page = response.pager.slide;
					$scope.total = response.pager.totalPage;
					$scope.current = response.current;
				})
				.error(function(error) {
					alert(error)
				});
			}
		}); 
	</script>
	<div class="container" ng-app="members" ng-controller="getMembers">
		<br>
		<a href="${pageContext.request.contextPath }/user/">用户列表</a>
		<a href="${pageContext.request.contextPath }/uploadify/multiUpload">上传文件</a>
		<br>
		<table class="table">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>注册日期</td>
				<td>电话</td>
				<td>邮箱</td>
			</tr>
			<tr ng-repeat="m in members">
				<td>{{m.id}}</td>
				<td>{{m.memberName}}</td>
				<td>{{m.regDate}}</td>
				<td>{{m.phone}}</td>
				<td>{{m.email}}</td>
			</tr>
		</table>
		<div class="page">
			<ul class="pagination">				
				<li><a ng-click="getMembers(pageNo-1)" href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>				
				<li ng-repeat="p in page"><a ng-click="getMembers(p)" href="javascript:void(0);">{{p}}</a></li>				
				<li><a class="" ng-click="getMembers(pageNo+1)" href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>				
			</ul>
		</div>
	</div>
</body>
</html>