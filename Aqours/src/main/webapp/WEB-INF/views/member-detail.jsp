<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function getQueryString(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	
</script>
<div class="container">	
	<table class="table">
		<tr>
			<td>${member.memberName }</td>
			<td>${member.email }</td>
			<td>${member.phone }</td>
			<td>${member.regDate }</td>
		</tr>
	</table>
</div>