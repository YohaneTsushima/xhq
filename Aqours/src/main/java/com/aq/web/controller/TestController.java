package com.aq.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aq.biz.MembersBiz;
import com.aq.entity.Members;

@Controller
@RequestMapping("/")
public class TestController{

	@Autowired
	private MembersBiz memberBiz;
	
	@RequestMapping("/")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping("member")
	@ResponseBody
	public Map<String, Object> getMember(){
		
		Map<String, Object> map = new HashMap<>();
		
		List<Members> lst = memberBiz.getAllMembers();
		
		map.put("members", lst);
		
		return map;
	}
}
