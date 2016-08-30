package com.aq.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aq.biz.MembersBiz;
import com.aq.entity.Members;
import com.aq.entity.Page;

@Controller
@RequestMapping("/")
public class HomeController{

	@Autowired
	private MembersBiz memberBiz;
	
	@RequestMapping("/")
	public String home(HttpSession session) {
		
		return "home";
	}
	
	@RequestMapping("member")
	@ResponseBody
	public Map<String, Object> getMember(Page page){
		
		// 1-2,3-4,5-6...
		//pageNo=pageNo!=null?pageNo:0;
		if(page.getPageNo() < 1){
			page.setPageNo(0);
		}
		page.setPageSize(2);
		
		//条数
		page.setTotalRecord(memberBiz.getMemberCount());
		
		if(page.getTotalPage() == 0){
			page.setTotalPage(1);
		}
		
		Map<String, Object> map = new HashMap<>();
		
		List<Members> lst = memberBiz.getAllMembers(page);
		
		map.put("members", lst);
		map.put("page", page);
		
		return map;
	}
	
	@RequestMapping("detail")
	public String member_detail(Model model, @RequestParam(name="id", required=false, defaultValue="0")int id){
		
		Members members = memberBiz.getMemberDetail(id);
		model.addAttribute("member", members);
		return "member-detail";
	}
}
