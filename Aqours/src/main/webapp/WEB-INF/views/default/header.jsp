<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<header>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

});
</script>
<div>
	<nav class="navbar navbar-default" role="navigation">
		<label class="navbar-brand" >欢迎你：${loginUser==''?loginUser.memberName:'游客' }</label>
		
		<ul class="nav navbar-nav navbar-right cd-switcher" role="search" style="margin-top: 8px;">
	    	<li><button type="submit" class="btn btn-warning">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li> 
	    	<li><button type="submit" class="btn btn-info theme-login">登陆</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	    </ul>
	</nav>
</div>
<div class="theme-popover">
	<div class="theme-poptit">
	<a href="javascript:;" title="关闭" class="close">×</a>
    <h3>登录</h3>
    <div class="theme-popbod dform">
    	<form class="theme-signin form-horizontal" name="loginform" action="" method="post">
        	<div class="form-group">
				<label for="memberName" class="col-sm-2 control-label">用户名</label>
				<div class="col-sm-5">
					<input type="text" name="memberName" class="form-control" id="memberName" placeholder="用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-5">
					<input type="password" name="password" class="form-control" id="password" placeholder="密码">
				</div>
			 </div>
			 <div class="form-group">
			 	<div class="col-sm-offset-2 col-sm-10">
	      			<div class="checkbox">
	        			<label> <input type="checkbox">记住我</label>
	      			</div>
    			</div>
			 </div>
			 <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-success">登陆</button>
			 	</div>
			 </div>
      	</form>
	</div>
    </div>
</div>
<div class="theme-popover-mask"></div>
<br>
</header>