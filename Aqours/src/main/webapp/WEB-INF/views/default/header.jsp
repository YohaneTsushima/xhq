<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<style>
.error{
	color: red;
	line-height: 35px;
}
</style>
<header>
<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover-login').slideDown(200);
	});
	$('.theme-poptit .close').click(function(){
		$('input').val('');
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	});
	
	$('.theme-regist').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover-regist').slideDown(200);
	});
	
	//登录验证
	$("#loginForm").validate({
		rules:{
			login_name: "required",
			password: "required"
		},
		messages:{
			login_name:"用户名不能为空",
			password:"密码不能为空"
		},
		highlight :function(element){
			$(element).closest('.form-group').addClass('has-error').removeClass('has-success'); 
		},
		success : function(label) {  
            label.closest('.form-group').removeClass('has-error').addClass('has-success');  
            label.remove();  
        },  
		errorPlacement:function(error, element){
			if (element.is(":input")) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
		}
	});
	
	//注册验证
	$("#registForm").validate({
		rules:{
			login_name: {
				required: true,
				remote:{
					url:"${pageContext.request.contextPath}/checkMemberName",
					type:"GET",
					dataType:"json",
					data:{
						memberName:function(){
							return $(".member_name").val();
						}
					}
				}
			},
			password: {
				required: true,
		        minlength: 6
			},
			confirm_password:{
				required:true,
				equalTo: "#password"
			},
			email:{
				required: true,
		        email: true
			}
		},
		messages:{
			login_name:{
				required:"用户名不能为空",
				remote:"用户已存在"
			},
			password:{
				required: "密码不能为空",
				minlength: "密码长度不能小于6"
			},
			confirm_password:{
				required: "请填写确认密码",
				equalTo: "2次密码不一致"
			},
			email:{
				required:"邮箱不能为空",
				email: "请输入一个正确的邮箱"
			}
		},
		highlight :function(element){
			$(element).closest('.form-group').addClass('has-error').removeClass('has-success'); 
		},
		success : function(label) {  
            label.closest('.form-group').removeClass('has-error').addClass('has-success');  
            label.remove();  
        },  
		errorPlacement:function(error, element){
			if (element.is(":input")) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
		}
	});
});

</script>
<div>
	<nav class="navbar navbar-default" role="navigation">
		<label class="navbar-brand" >欢迎你：${loginUser==''?loginUser.memberName:'游客' }</label>
		
		<ul class="nav navbar-nav navbar-right cd-switcher" role="search" style="margin-top: 8px;">
	    	<li><button type="submit" class="btn btn-warning theme-regist">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li> 
	    	<li><button type="submit" class="btn btn-info theme-login">登陆</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	    </ul>
	</nav>
</div>
<!-- 登陆 -->
<div class="theme-popover theme-popover-login">
	<div class="theme-poptit">
		<a href="javascript:;" title="关闭" class="close">×</a>
	    <h3>登录</h3>
	</div>
    <div class="theme-popbod dform">
    	<form class="theme-signin form-horizontal" id="loginForm" name="loginform" action="" method="post">
        	<div class="form-group" style="height: 30px;">
				<label for="login_name" class="col-sm-2 control-label">登陆名</label>
				<div class="col-sm-5">
					<input type="text" name="login_name" class="form-control login_name" placeholder="登陆名">
				</div>
			</div>
			<div class="form-group" style="height: 30px;">
				<label for="password" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-5">
					<input type="password" name="password" class="form-control" placeholder="密码">
				</div>
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
			      <button type="submit" class="btn btn-success confirm">登陆</button>
			 	</div>
			 </div>
      	</form>
	</div>
</div>
<!-- 注册 -->
<div class="theme-popover theme-popover-regist">
	<div class="theme-poptit">
		<a href="javascript:;" title="关闭" class="close">×</a>
	    <h3>快速注册</h3>
	</div>
    <div class="theme-popbod dform">
    	<form class="theme-signin form-horizontal" id="registForm" name="registform" action="" method="post">
        	<div class="form-group" style="height: 30px;">
				<label for="login_name" class="col-sm-2 control-label">登陆名</label>
				<div class="col-sm-5">
					<input type="text" name="login_name" class="form-control" placeholder="登陆名">
				</div>
			</div>
			<div class="form-group" style="height: 30px;">
				<label for="password" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-5">
					<input type="password" name="password" class="form-control" id="password" placeholder="密码">
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
					<input type="text" name="email" class="form-control" placeholder="Email">
				</div>
			 </div>
			 <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-warning register">注册</button>
			 	</div>
			 </div>
      	</form>
	</div>
</div>
<div class="theme-popover-mask"></div>
<br>
</header>