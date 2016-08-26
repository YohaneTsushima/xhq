<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var url="${pageContext.request.contextPath }/member";
	var app = angular.module('myApp', []);
	app.controller('memberCtrl', function($scope, $http){
		$http.get(url)
			.success(function(response){
				$scope.members = response.members;
			}).error(function(error){
				alert(error);
			});
	});
	$(function(){
		$(".btn1").click(function(){
			var html = "<ul><li>你好</li</ul>";
			$(".msg").html(html);
		});
	})
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
	<input id="d11" type="text"  onClick="WdatePicker()" readonly="readonly">
	<input type="button" name="btn1" class="btn1" value="点我看看">
	<div class="msg">
	
	</div>
</div>