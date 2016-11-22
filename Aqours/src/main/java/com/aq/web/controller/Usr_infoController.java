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
		
		response.setContentType("text/html; charset=utf-8");
		try {
			JSONObject result = user_infoBiz.checkValidCode(validCode, session);
			if(result.getBoolean("success")){
				usr_info.setRegDate(new Date());
				user_infoBiz.registerUsr(usr_info);
			}
			response.getWriter().println(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public void doLogin(String login_name, String password, HttpSession session, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		try {
			JSONObject result = user_infoBiz.checkLogin(user_infoBiz.getUsrByLoginName(login_name), password);
			if(result.getBoolean("success")){
				Usr_info usr = user_infoBiz.getUsrByLoginName(login_name);
				session.setAttribute("loginUser", usr);
			}
			response.getWriter().println(result.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpSession session){
		session.removeAttribute("loginUser");
		return "redirect:/home/";
	}
}
