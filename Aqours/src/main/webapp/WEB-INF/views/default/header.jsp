<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<header>
<div>
	<nav class="navbar navbar-default" role="navigation">
		<label class="navbar-brand" >欢迎你：${loginUser==''?loginUser.memberName:'游客' }</label>
		
		<ul class="nav navbar-nav navbar-right cd-switcher" role="search" style="margin-top: 8px;">
	    	<li><button type="submit" class="btn btn-warning">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li> 
	    	<li><button type="submit" class="btn btn-info">登陆</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	    </ul>
	</nav>
</div>
<br>
</header>