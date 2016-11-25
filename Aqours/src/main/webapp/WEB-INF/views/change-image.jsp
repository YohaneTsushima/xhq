<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div style="height: auto; width: 100%;">
	<form name="form" action="${pageContext.request.contextPath }/edit_usr/uploadImage" class="form-horizontal" method="post" enctype="multipart/form-data">
		<div class="uploadImg" style="width: 100%; height:auto;">
			<img alt="" src="" id="cutimg" style=" float: left; margin-right: 20px;"/> &nbsp;&nbsp;&nbsp;&nbsp;
			<div class="clear"></div>
			<br>
	        <input type="file" name="imgFile" id="upImg" onchange="addFile(this);"><br>
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
	    <script src="${pageContext.request.contextPath }/js/image.js"></script>
	</form>
</div>