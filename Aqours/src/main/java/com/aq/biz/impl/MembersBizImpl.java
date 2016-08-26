package com.aq.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aq.biz.MembersBiz;
import com.aq.dao.MembersDao;
import com.aq.entity.Members;

@Service("membersBiz")
public class MembersBizImpl implements MembersBiz{

	@Autowired
	private MembersDao membersDao;
	
	@Override
	public List<Members> getAllMembers() {
		// TODO 获取所有成员的方法
		return membersDao.getAllMembers();
	}
/*
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MembersBiz membersBiz = (MembersBiz) context.getBean("membersBiz");
		List<Members> mList = membersBiz.getAllMembers();
		for (Members members : mList) {
			System.out.println(members.getMemberName());
			System.out.println(members.getEmail());
			System.out.println(members.getPhone());
		}
	}*/

	@Override
	public Members getMemberDetail(int id) {
		// TODO Auto-generated method stub
		return membersDao.getMemberDetail(id);
	}
}
