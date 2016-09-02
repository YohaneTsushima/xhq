<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<header>
<div>
	<nav class="navbar navbar-default" role="navigation">
		<a class="navbar-brand" href="#">欢迎你：${loginUser==''?loginUser.memberName:'游客' }</a>
		<form class="navbar-form navbar-right" role="search" >
	    	<button type="submit" class="btn btn-warning">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<button type="submit" class="btn btn-info">登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </form>
	</nav>
</div>
<br>
</header>