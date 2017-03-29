<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="height: auto; width: 100%;">
	<script type="text/javascript">
		$(function(){
			$("#user-info").validate({
				onblur:true,
				rules:{
					old_password: {
						required: true,
						remote: "/JFinal/user/checkPwd"
					},
					new_password:{
						required: true,
						minlength: 6
					},
					confirm_password:{
						required: true,
						minlength: 6,
						equalTo: "#new_password"
					}
				},
				messages:{
					old_password: {
						required: "请填写原密码",
						remote: "原密码错误"
					},
					new_password:{
						required: "请输入新密码",
						minlength: "密码长度不能小于6字符"
					},
					confirm_password:{
						required: "请确认密码",
						minlength: "密码长度不能小于6字符",
						equalTo: "2次密码输入不一致"
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
				},
				invalidHandler : function(){
					return false;
				}/* ,
				submitHandler : function(){
					$.ajax({
						url:"/JFinal/user/changePwd",
						type:'post',
						data:$("#user-info").serialize(),
						dataType: "json",
						error:function(){
							alert("失败");
						},
						success:function(result){
							try{
								debugger;
								$("#user-info").html('');
								$(".rupdate_esult").text(result.msg);
							}catch (e){
								alert(e);
							}
						}
						
					});
				}  */
			});
		});
	</script>
	<form action="${pageContext.request.contextPath }/user/uploadOrEdit" class="form-horizontal" id="user-info" method="post" enctype="multipart/form-data">
		<c:if test="${action == 'change_image' }">
			<div class="uploadImg" style="width: 100%; height: auto;">
				<img alt="" src="" id="cutimg" style="float: left; margin-right: 20px;" />&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="clear"></div>
				<br>
				<input type="file" name="imgFile" id="upImg" onchange="addFile(this)"><br>
			</div>
			<div class="boxFooter">
				<input type="hidden" name="x1" value="0">
		        <input type="hidden" name="y1" value="0">
		        <input type="hidden" name="height" value="100">
		        <input type="hidden" name="width" value="100"> 
		        <input type="submit" name="confirm" class="btn btn-info" id="subPhoto" value="确  定"/>
		        <input type="button" name="" onclick="clearfFile();" class="btn btn-info" value="重新选择">
		        <div id="imgmsg"></div>
			</div>
			<script type="text/javascript" src="${pageContext.request.contextPath }/contents/js/image.js"></script>
		</c:if>
		<c:if test="${action == 'edit_info' }">
			<div class="clear"></div>
			<div class="form-group">
				<label for="login_name" class="col-sm-2 control-label">登陆名</label>
				<div class="col-sm-5">
					<input type="text" name="user.login_name" readonly="readonly" value="${loginUser.login_name }" class="form-control" placeholder="登陆名">
				</div>
			</div>
			<div class="form-group">
				<label for="login_name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-5">
					<input type="text" name="user.usr_name" value="${loginUser.usr_name }" class="form-control" placeholder="姓名">
				</div>
			</div>
			 <div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-5">
					<input type="text" name="user.email" value="${loginUser.email }" class="form-control" placeholder="Email">
				</div>
			 </div>
			 <div class="form-group">
				<label for="email" class="col-sm-2 control-label">电话</label>
				<div class="col-sm-5">
					<input type="text" name="user.phone" value="${loginUser.phone }" class="form-control" placeholder="电话">
				</div>
			 </div>
			 <div class="form-group">
				<label for="email" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-5">
					<input type="text" name="user.address" value="${loginUser.address }" class="form-control" placeholder="地址">
				</div>
			 </div>
			 <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
			      <button style="margin-right: 30px;" type="submit" class="btn btn-success">提交</button>
			      <button type="reset" class="btn btn-fail">重置</button>
			 	</div>
			 </div>
		</c:if>
	</form>
	<c:if test="${action == 'changePwd' }">
		<form action="${pageContext.request.contextPath }/user/checkPwd" class="form-horizontal" method="post">
			<div class="form-group" style="height: 30px;">
				<label for="login_name" class="col-sm-2 control-label">原密码</label>
				<div class="col-sm-5">
					<input type="password" name="old_password" class="form-control" placeholder="原密码">
				</div>
			</div>
			<div class="form-group" style="height: 30px;">
				<label for="login_name" class="col-sm-2 control-label">新密码</label>
				<div class="col-sm-5">
					<input type="password" name="new_password" id="new_password" class="form-control" placeholder="新密码">
				</div>
			</div>
			 <div class="form-group" style="height: 30px;">
				<label for="email" class="col-sm-2 control-label">确认密码</label>
				<div class="col-sm-5">
					<input type="password" name="confirm_password" class="form-control" placeholder="确认密码">
				</div>
			 </div>
			 <div class="form-group" >
				<div class="col-sm-offset-2 col-sm-10">
			      <button style="margin-right: 30px;" type="submit" class="btn btn-success">提交</button>
			      <button type="reset" class="btn btn-fail">重置</button>
			 	</div>
			 </div>
		</form>
	</c:if>
	<%-- <c:if test="${action == 'activited' }">
		<form action="${pageContext.request.contextPath }/user/activited" class="form-horizontal" method="post">
			<div class="form-group" style="height: 30px;">
				<label for="login_name" class="col-sm-2 control-label">原密码</label>
				<div class="col-sm-5">
					<input type="password" name="old_password" class="form-control" placeholder="原密码">
				</div>
			</div>
		</form>
	</c:if> --%>
</div>