<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="default/header.jsp" %>
<body>
	<div class="container" id="vueApp">
		
		<div id="change">
			<span v-bind:title="text">Hover your mouse over me for a few seconds to see my dynamically bound title!</span>
		</div>
		<div id="app3">
			<p v-if="seen">我看见你们了</p>
		</div>
		<div id="members">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<td>编号</td>
						<td>姓名</td>
						<td>注册日期</td>
						<td>电话</td>
						<td>邮箱</td>
					</tr>
				</thead>
				<tbody>
					<tr v-for="m in members">
						<td v-text="m.id"> </td>
						<td v-text="m.memberName"> </td>
						<td v-text="m.regDate"> </td>
						<td v-text="m.phone"> </td>
						<td v-text="m.email"> </td>
					</tr>
				</tbody>
			</table> 
			<div class="page" id="change">
				<ul class="pagination">
					<li><a @click="getMembers(pageNo-1)" href="" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a> </li>
					<li v-for="s in slide"><a href="" v-on:click="getMembers(s)" aria-label="Previous">{{s}}</a></li>
					<li><a href="" @click="getMembers(pageNo+1)" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		 
		var app2 = new Vue({
			el: "#change",
			data: {
				text: "You loaded this page on " + new Date()
			}
		})
		
		var app3 = new Vue({
			el: "#app3",
			data: {
				seen: false
			}
		});
		
		var app4 = new Vue({
			el: "#members",
			data: {
				members: [],
				pageNo: 0,
				pageSize: 0,
				slide: [],
				total: 0
			},
			methods: {
				getMembers: function(pageNo){
					var _self = this;
					$.ajax({
						url: "/JFinal/getMembers",
						data: pageNo,
						type: "get",
						success: function(rep){
							_self.members = rep.members;
							_self.pageNo = rep.pager.pageNo;
							_self.pageSize = rep.pager.pageSize;
							_self.slide = rep.pager.slide;
							_self.total = rep.pager.total;
						}
					});
				}
			},
			created: function(){
				this.$http.get('/JFinal/getMembers?pageNo=' + this.pageNo).then(function(datas) {
					this.members = datas.data.members;
					this.pageNo = datas.data.pager.pageNo;
					this.pageSize = datas.data.pager.pageSize;
					this.slide = datas.data.pager.slide;
					this.total = datas.data.pager.totalPage;
				}, function(datas) {
					alert(datas.data.msg);
				})
			} 
			
		}) 
		
		/* var pageAPp = new Vue({
			el: "#change",
			data: {
				members: [],
				pageNo: 0,
				pageSize: 0,
				slide: [],
				total: 0
			},
			methods: {
				getMembers: function(pageNo){
					this.$http.get('/JFinal/getMembers?pageNo='+pageNo).then(function(datas) {
						this.members = datas.data.members;
						this.pageNo = datas.data.pager.pageNo;
						this.pageSize = datas.data.pager.pageSize;
						this.slide = datas.data.pager.slide;
						this.total = datas.data.pager.totalPage;
					}, function(datas) {
						alert(datas.data.msg);
					})
				}
			}		
		}) */
	</script>
</body>
</html>