package com.aq.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aq.entity.Usr_info;

public class SecurityInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Usr_info user = (Usr_info) request.getSession().getAttribute("loginUser");
		if(user == null){
			response.sendRedirect("localhost:8080/Aqours/");
			return false;
		} else {
			return true;
		}
	}

}
