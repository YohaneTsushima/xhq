package com.aq.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aq.biz.MembersBiz;
import com.aq.biz.ValidCodeBiz;
import com.aq.entity.Members;
import com.aq.entity.Page;

public class TestValidCode extends BaseJunitTest{
	
	@Autowired
	private ValidCodeBiz biz;
	
	@Autowired
	private MembersBiz memberBiz;
	
	@Test
	public void testValidCode(){
		Page page = new Page();
		if(page.getPageNo() < 1){
			page.setPageNo(0);
		}
		page.setPageSize(5);
		
		//条数
		page.setTotalRecord(memberBiz.getMemberCount());
		
		if(page.getTotalPage() == 0){
			page.setTotalPage(1);
		}
		if((page.getPageNo()/page.getPageSize()) >= page.getTotalPage()){
			page.setPageNo((page.getTotalPage()-1) * page.getPageSize());
		}
		List<Members> list = memberBiz.getAllMembers(page);
		for (Members members : list) {
			System.out.println(members.getMemberName());
		}
	}
}
