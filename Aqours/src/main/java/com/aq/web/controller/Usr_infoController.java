package com.aq.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aq.biz.User_infoBiz;
import com.aq.entity.Usr_info;

import net.sf.json.JSONObject;

/***
 * @author chika
 * 用户注册，登录名判断是否重复注册，验证码判断
 */
@Controller
@RequestMapping("/usr_info")
public class Usr_infoController {

	@Autowired
	private User_infoBiz user_infoBiz;
	
	@RequestMapping("/usrChk")
	public void chkUsr(String login_name, HttpServletResponse response) throws IOException{
		
		Usr_info usr_info = user_infoBiz.getUsrByLoginName(login_name);
		boolean flag = false;
		if(usr_info == null){
			flag = true;
		}
		response.getWriter().println(flag);
	}
	
	@RequestMapping("/validChk")
	public void chkValid(String validCode, HttpServletResponse response, HttpSession session) throws IOException{
		String valid = (String) session.getAttribute("code");
		boolean flag = false;
		if(validCode.equalsIgnoreCase(valid)){
			flag = true;
		}
		response.getWriter().println(flag);
	}
	
	@RequestMapping("/usrRegister")
	@ResponseBody
	public void register(Usr_info usr_info, String validCode, HttpSession session, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		response.setContentType("text/html; charset=utf-8");
		try {
			usr_info.setUsr_type(0);
			usr_info.setRegDate(new Date());
			String valid = (String) session.getAttribute("code");
			if(!validCode.equalsIgnoreCase(valid)){
				result.put("msg", "验证码错误");
				result.put("success", false);
				result.put("user", null);
			}else {
				user_infoBiz.registerUsr(usr_info);
				result.put("success", true);
				result.put("msg", "注册成功");
				result.put("user", usr_info.getLogin_name());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}		
		response.getWriter().println(result.toString());		
	}
}
