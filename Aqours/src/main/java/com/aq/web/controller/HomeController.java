package com.aq.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("login")
	public String loggin(){
		return "login";
	}
	
	@RequestMapping("member")
	@ResponseBody
	public Map<String, Object> getMember(Page page){
		
		// 1-2,3-4,5-6...
		//pageNo=pageNo!=null?pageNo:0;
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
		
		List<Members> lst = memberBiz.getAllMembers(page);
		Map<String, Object> map = new HashMap<>();
		
		map.put("members", lst);
		map.put("page", page);
		
		return map;
	}
	
	@RequestMapping(value="detail", method=RequestMethod.GET)
	public String member_detail(Model model, @RequestParam(name="id", required=false, defaultValue="0")int id){
		
		Members members = memberBiz.getMemberDetail(id);
		model.addAttribute("member", members);
		return "member-detail";
	}
	
	@RequestMapping(value = "m-detail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> member_detail(@RequestParam(name = "id", required = false, defaultValue = "0")int id){
		
		Map<String, Object> map = new HashMap<>();
		Members members = memberBiz.getMemberDetail(id);
		map.put("member", members);
		
		return map;
	}
}
