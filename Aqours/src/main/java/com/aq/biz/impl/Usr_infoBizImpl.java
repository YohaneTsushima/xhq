package com.aq.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aq.biz.User_infoBiz;
import com.aq.dao.User_infoDao;
import com.aq.entity.Usr_info;

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

}
