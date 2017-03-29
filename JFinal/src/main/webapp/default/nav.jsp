<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.error, .errorValid, .errorUser, .errorPwd{
	color: red;
	line-height: 35px;
}
.logout{
	padding-right: 20px;
	line-height: 40px;
}
.imgHead{
	margin-bottom: 15px;	
	cursor: pointer;
}

</style>
<header>
	<nav class="navbar navbar-default" role="navigation" style="margin: 0px auto">
		<label class="navbar-brand" style="line-height: 32px;">欢迎你：${loginUser != null ? loginUser.login_name : '游客' }</label>
		<ul class="nav navbar-nav navbar-right logout" role="search" style="margin-top: 8px;">
			<li><img alt="" class="imgHead" width="50px" height="50px" src="${pageContext.request.contextPath }/contents/images/head/${loginUser.id }_head_cut.jpg"></li>
			<li><a href="/JFinal">首页</a></li>
			<c:if test="${loginUser != null }">
				<li><img src="" /></li>
				<li><a href="${pageContext.request.contextPath }/user/edit">修改个人资料</a></li>
				<li><a href="${pageContext.request.contextPath }/user/logout">注销</a></li>
			</c:if>
			<c:if test="${loginUser == null }">
		    	<li><button class="btn btn-warning theme-regist">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li> 
		    	<li><button class="btn btn-info theme-login">登陆</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</c:if>
		</ul>
	</nav>
	<!-- 注册 -->
	<div class="theme-popover theme-popover-regist">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
		    <h4>快速注册</h4>
		</div>
	    <div class="theme-popbod dform">
	    	<form class="theme-signin form-horizontal" id="registForm" name="registform" action="" method="post">
	        	<div class="form-group" style="height: 30px; margin-top: -50px;">
					<label for="login_name" class="col-sm-2 control-label">登陆名</label>
					<div class="col-sm-5">
						<input type="text" name="user.login_name" class="form-control" placeholder="登陆名">
					</div>
				</div>
				<div class="form-group" style="height: 30px;">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-5">
						<input type="password" name="user.password" class="form-control" id="password" placeholder="密码">
					</div>
				 </div>
				 <div class="form-group" style="height: 30px;">
					<label for="confirm_password" class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-5">
						<input type="password" name="confirm_password" class="form-control"  placeholder="确认密码">
					</div>
				 </div>
				 <div class="form-group" style="height: 30px;">
					<label for="email" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-5">
						<input type="text" name="user.email" class="form-control" placeholder="Email">
					</div>
				 </div>
				 <div class="form-group validCode" style="height: 30px;">
					<label for="email" class="col-sm-2 control-label">验证码</label>
					<div class="col-sm-3">
						<input type="text" name="validCode" class="form-control" placeholder="请输入验证码">
					</div>
					<label class="errorValid"></label>
				 </div>
				 <div class="form-group" style="height: 30px;">
				 	<label for="email" class="col-sm-2 control-label"></label>
					<div class="col-sm-5">
						<a href="javascript:void(0);" onclick="changeImg()"><img style="margin-top: 8px;" id="imgObj" alt="验证码" src="${pageContext.request.contextPath }/user/validCode/create"></a>
					</div>
				 </div>
				 <div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
				      <button style="margin-right: 30px; margin-bottom: 30px;" type="submit" class="btn btn-warning register">注册</button>
				      <button style="margin-bottom: 30px;" onclick="closeWindow();" type="button" class="btn btn-warning">关闭</button>
				 	</div>
				 </div>
	      	</form>
		</div>
	</div>
	<!-- 登陆 -->
	<div class="theme-popover theme-popover-login">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
		    <h4>登录</h4>
		</div>
	    <div class="theme-popbod dform">
	    	<form class="theme-signin form-horizontal" id="loginForm" name="loginform" action="" method="post">
	        	<div class="form-group" style="height: 30px; margin-top: -50px;">
					<label for="login_name" class="col-sm-2 control-label">登陆名</label>
					<div class="col-sm-5">
						<input type="text" id="login_name" name="user.login_name" class="form-control login_name" placeholder="登陆名">						
					</div>
					<label class="errorUser"></label>
				</div>
				<div class="form-group" style="height: 30px;">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-5">
						<input type="password" name="user.password" class="form-control" placeholder="密码">
					</div>
					<label class="errorPwd"></label>
				 </div>
				 <div class="form-group remember" style="height: 30px;">
				 	<div class="col-sm-offset-2 col-sm-10">
		      			<div class="checkbox">
		        			<label> <input type="checkbox">记住我</label>
		      			</div>
	    			</div>
				 </div>
				 <div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
				      <button style="margin-bottom: 10px;" type="submit" class="btn btn-success confirm">登陆</button>
				 	</div>
				 </div>
	      	</form>
		</div>
	</div>
	<div class="theme-popover-mask"></div>
	<br>
</header>
