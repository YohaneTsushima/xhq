package com.aq.biz;

import javax.servlet.http.HttpSession;

import com.aq.entity.Usr_info;

import net.sf.json.JSONObject;

public interface User_infoBiz {

	void registerUsr(Usr_info usr_info);
	void updateUsr(Usr_info usr_info);
	Usr_info getUsrByLoginName(String login_name);
	JSONObject checkValidCode(String validCode, HttpSession session) throws Exception;
	JSONObject checkLogin(Usr_info usr_info, String password);
}
