package com.aq.biz.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aq.biz.User_infoBiz;
import com.aq.dao.User_infoDao;
import com.aq.entity.Usr_info;

import net.sf.json.JSONObject;

@Service("usr_infoBiz")
public class Usr_infoBizImpl implements User_infoBiz{
	
	@Autowired
	private User_infoDao usr_infoDao;

	@Override
	public void registerUsr(Usr_info usr_info) {
		// TODO Auto-generated method stub
		usr_infoDao.registerUsr(usr_info);
	}

	@Override
	public Usr_info getUsrByLoginName(String login_name) {
		// TODO Auto-generated method stub
		return usr_infoDao.getUsrByLoginName(login_name);
	}

	@Override
	public void updateUsr(Usr_info usr_info) {
		// TODO Auto-generated method stub
		usr_infoDao.updateUsr(usr_info);
	}

	@Override
	public JSONObject checkValidCode(String validCode, HttpSession session) throws Exception{
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		String valid = (String) session.getAttribute("code");
		if(validCode.equalsIgnoreCase(valid)){
			result.put("success", true);
			result.put("msg", "注册成功");
		}else {
			result.put("msg", "验证码错误");
			result.put("success", false);
			
		}
		
		return result;
	}

	@Override
	public JSONObject checkLogin(Usr_info usr_info, String password) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		if(usr_info == null){
			result.put("errorUser", "没有该用户");
			result.put("success", false);
		}else if(!usr_info.getPassword().equals(password)){
			result.put("errorPwd", "密码错误");
			result.put("success", false);
		}else {
			result.put("success", true);
			result.put("msg", "登陆成功,欢迎你：" + usr_info.getLogin_name());
			result.put("loginUser", usr_info);
		}
		
		return result;
	}

}
