package com.aq.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aq.biz.User_infoBiz;
import com.aq.entity.Usr_info;

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
	
	@RequestMapping("/usrRegister")
	@ResponseBody
	public void register(Usr_info usr_info){
		
		usr_info.setUsr_type(0);
		usr_info.setRegDate(new Date());
		user_infoBiz.registerUsr(usr_info);
	}
}
