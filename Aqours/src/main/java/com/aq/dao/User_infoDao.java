package com.aq.dao;

import com.aq.entity.Usr_info;

public interface User_infoDao {

	void registerUsr(Usr_info usr_info);
	void updateUsr(Usr_info usr_info);
	Usr_info getUsrByLoginName(String login_name);
}
