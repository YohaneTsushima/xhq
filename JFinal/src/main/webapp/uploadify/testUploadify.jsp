<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/contents/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/contents/js/uploadify/jquery.uploadify.js"></script>
<link href="${pageContext.request.contextPath }/contents/css/uploadify.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#uploadify").uploadify({
			'swf'       : '${pageContext.request.contextPath }/contents/js/uploadify/uploadify.swf',  
			'width'          :'60',  
	        'uploader'       : '',
	        'queueID'        : 'fileQueue', 
	        'auto'           : false,  
	        'method'         : 'get',
      		'multi'          : true,
      		'wmode'          : 'transparent',  
      		'simUploadLimit' : 999,
      		'fileSizeLimit'  : '5MB',
      		'buttonText'     : '选择模板',
      		'queueSizeLimit' : 10,
      		'buttonClass'    : 'change-button',
      		'overrideEvents' : ['onSelectError', 'onDialogClose'],
      		'fileTypeExts'   : '*.docx',
      		'onAllComplete'  : function(event, data){
      			debugger;
      			$('#result').html(data.filesUploaded +'个模板上传成功');
      			if(data.indexOf('') != -1){
      				var tmp = $.parseJSON(data);
      				reloadTemplates(tmp, templateAction, templateType, btn);
      			}
      		},
      		'onSelectError'  : function(file, errorCode, errorMsg){
      			switch(errorCode){
      				case -100:
      					alert("上传文件次数超出限制，你还能上传" + $("#uploadify").uploadify('settings', 'queueSizeLimit') + "个文件");
      					break;
      				case -110:
      					alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#uploadify').uploadify('settings', 'fileSizeLimit') + "大小！");
      					break;
      				case -130:
      					alert("文件 [" + file.name + "] 类型不正确！请上传docx类型文件");
      					break;
      			}
      		},
      		'onUploadStart'  : function(file){
      			debugger;
      			var param = $(".uploadTemplateForm").serialize();
      			$('#uploadify').uploadify('settings', 'formData', {
      				'action': "upload"
      			});
      		}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<input type="file" id="uploadify" name="uploadify">
		<p>
		<a href="javascript:$('#uploadify').uploadify('upload', '*')">开始上传</a>
		<a href="javascript:$('#uploadify').uploadify('cancel', '*')">取消上传</a>
		<div id="fileQueue" style="width: 50%;height: 300px; border: 2px solid green; overflow: scroll;"></div>
	</div>
</body>
</html>