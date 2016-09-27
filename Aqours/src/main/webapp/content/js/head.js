jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover-login').slideDown(200);
	});
	$('.theme-poptit .close').click(function(){
		closeWindow();
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
		},
		invalidHandler: function(){
			return false;
		},
		submitHandler: function() {
			$(".errorUser").text('');
			$(".errorPwd").text('');
			debugger;
			$.ajax({
				url:"/Aqours/usr_info/doLogin",
				data:$("#loginForm").serialize(),
				success:function(formData, jqForm, options){
					try {
						debugger;
						var result = eval("(" + formData + ")");
						if(result.success){
							alert('欢迎你:'+result.msg);
							location.reload();
						}else{
							if(result.errorUser != undefined){
								$(".errorUser").text(result.errorUser);
							}else if(result.errorPwd != undefined){
								$(".errorPwd").text(result.errorPwd);
							}
							
						}
					} catch (e) {
						// TODO: handle exception
						alert('登陆异常');
					}
				}
			});
		}
	});
	$("#login_name").focus();
	
	$('input[name=validCode]').focus(function(){
		$(".errorValid").text('');
	});
	$('input[name=login_name]').focus(function(){
		$(".errorUser").text('');
	});
	$('input[name=password]').focus(function(){
		$(".errorPwd").text('');
	});
		
	
	//注册验证
	$("#registForm").validate({
		onblur:true,
		rules:{
			login_name: {
				required: true,
				remote: "/Aqours/usr_info/usrChk"
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
			},
			validCode:{
				remote: "/Aqours/usr_info/validChk",
				required:true
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
			},
			validCode:{
				remote: "验证码错误",
				required:"请填写验证码"
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
		},invalidHandler : function(){
			return false;
		},submitHandler : function(){
			$(".errorValid").text('');
			$.ajax({
				url:"/Aqours/usr_info/usrRegister",
				type:'post',
				data:$("#registForm").serialize(),
				error:function(){
					alert("失败");
				},
				success:function(formData, jqForm, options){
					try{
						debugger;
					 	var result = eval("(" + formData + ")");
						if(result.success){
							alert(result.msg+ ',欢迎你' + result.user);
							closeWindow();
						}else{
							$(".errorValid").text(result.msg);
						}
					}catch (e){
						alert('注册异常');
					}
				}
				
			});
		}
	});
});

function changeImg() {
	var imgSrc = $("#imgObj"); 
	$(".errorValid").text('');
	if(imgSrc.attr("src") != '/Aqours/images/err.gif'){	
		var src = imgSrc.attr("src");
	    imgSrc.attr("height", "20");
	    imgSrc.attr("width", "90");
	    imgSrc.attr("src", "/Aqours/images/err.gif");
	    setTimeout(function() {
	    	imgSrc.attr("src", chgUrl(src));
	    }, 1000);
	}
}

function closeWindow(){
	$('input').val('');
	$('.errorValid').text('');
	$('.error').text('');
	$('.theme-popover-mask').fadeOut(100);
	$('.theme-popover').slideUp(200);
}
  
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    //url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
      url = url + "×tamp=" + timestamp;
    } else {
      url = url + "?timestamp=" + timestamp;
    }
    return url;
}
