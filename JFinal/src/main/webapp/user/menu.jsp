<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-2">
	<div class="list-group">
	  <a href="javascript:void(0)" class="list-group-item active">修改个人信息</a>
	  <a href="${pageContext.request.contextPath }/user/edit/action-change_image" class="list-group-item">修改头像</a>
	  <a href="${pageContext.request.contextPath }/user/edit/action-edit_info" class="list-group-item">个人基础信息</a>
	  <a href="${pageContext.request.contextPath }/user/edit/action-changePwd" class="list-group-item">修改密码</a>
	  <a href="${pageContext.request.contextPath }/user/activited" class="list-group-item">激活账号</a>
	</div>
	<script type="text/javascript">
		function activited(){
			debugger;
			$.ajax({
				url: "/JFinal/user/activited",
				type: "post",
				success: function(result){
					if(result.success){
						alert(result.msg);
					} else {
						alert("激活失败");
					}
				},
				error: function(result){
					alert("加载数据时出现网络异常");
				}
			});
		}
	</script>
</div>