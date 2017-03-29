/**
 * http://usejsdoc.org/
 */
$(document).ready(function () { 
  //提交图片剪切信息到后台
	$('<div><img src="" id="prew" style="position: relative;" /><div>').css({
        position: 'relative',
        overflow: 'hidden',
        width: '150px',
        height: '150px'
	}).insertAfter($('#cutimg'));
}); 

function preview(img, selection){
	var scaleX = 150 / (selection.width || 1);
	var scaleY = 150 / (selection.height || 1);
	
	$("#cutimg + div > img").css({
		width: Math.round(scaleX * img.width) + 'px',
        height: Math.round(scaleY * img.height) + 'px',
        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
	});
}

function clearfFile(){
	$('#cutimg').removeAttr('src'); 
	$('#prew').removeAttr('src');
	$("#cutimg").val("");
	$("#upImg").val("");
	$("#cutimg").width("");
	$("#cutimg").height("");
}

function addFile(btn){
	debugger;
	var filePath = $(btn).val();
	var extStart = filePath.lastIndexOf(".");
	var ext = filePath.substring(extStart, filePath.length).toUpperCase();
	var reader = new FileReader();
	if(ext == ".BMP" || ext == ".PNG" || ext == ".GIF" || ext == ".JPG" || ext == ".JPEG"){
		reader.readAsDataURL(btn.files[0]);
		reader.onload = function (e){
			debugger;
			$('#cutimg').removeAttr('src');  
	        $('#cutimg').attr('src', e.target.result); 
	        if($("#cutimg").width() > 700 || $("#cutimg").height() > 800){
	        	clearfFile();
				alert("分辨率过高，请上传分辨率700以下的图片");
				return;
	        } else if($("#cutimg").width() < 700 && $("#cutimg").width() > 300){
	        	
	        }
	        
	        $('#prew').removeAttr('src');  
	        $('#prew').attr('src', e.target.result); 
	        $('#cutimg').imgAreaSelect({ x1:0, y1:0, x2:100, y2:100, selectionColor:'aqua', selectionOpacity:0.3, aspectRatio: '1:1', maxWidth: 500, maxHeight: 500, onSelectChange: preview, handles: true, onSelectEnd: function(img, selection){
	   		 	$('input[name="x1"]').val(selection.x1);
	            $('input[name="y1"]').val(selection.y1);
	            $('input[name="height"]').val(selection.height);
	            $('input[name="width"]').val(selection.width);     
	        } });
		}
	} else {
		alert("上传的格式并非图片格式，请上传png,gif,jpeg,jpg格式的文件");
		$(btn).val("");
	}
}