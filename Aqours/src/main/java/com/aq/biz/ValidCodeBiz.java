package com.aq.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidCodeBiz {

	void validCode(HttpServletRequest request, HttpServletResponse response);
}
