package com.aq.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aq.biz.User_infoBiz;
import com.aq.biz.impl.FileUploadUtil;
import com.aq.biz.impl.ImageCut;
import com.aq.entity.Usr_info;

import net.sf.json.JSONObject;

/***
 * @author chika
 * 用户注册，登录名判断是否重复注册，验证码判断
 */
@Controller
@RequestMapping("/usr_info")
public class Usr_infoController {

	@Autowired
	private User_infoBiz user_infoBiz;
	
	@RequestMapping("/usrChk")
	public void chkUsr(String login_name, HttpServletResponse response) throws IOException{
		
		Usr_info usr_info = user_infoBiz.getUsrByLoginName(login_name);
		boolean flag = false;
		if(usr_info == null){
			flag = true;
		}
		response.getWriter().println(flag);
	}
	
	@RequestMapping("/validChk")
	public void chkValid(String validCode, HttpServletResponse response, HttpSession session) throws IOException{
		String valid = (String) session.getAttribute("code");
		boolean flag = false;
		if(validCode.equalsIgnoreCase(valid)){
			flag = true;
		}
		response.getWriter().println(flag);
	}
	
	@RequestMapping("/usrRegister")
	@ResponseBody
	public void register(Usr_info usr_info, String validCode, HttpSession session, HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html; charset=utf-8");
		try {
			JSONObject result = user_infoBiz.checkValidCode(validCode, session);
			if(result.getBoolean("success")){
				usr_info.setRegDate(new Date());
				user_infoBiz.registerUsr(usr_info);
			}
			response.getWriter().println(result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public void doLogin(String login_name, String password, HttpSession session, HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		try {
			JSONObject result = user_infoBiz.checkLogin(user_infoBiz.getUsrByLoginName(login_name), password);
			if(result.getBoolean("success")){
				Usr_info usr = user_infoBiz.getUsrByLoginName(login_name);
				session.setAttribute("loginUser", usr);
				response.getWriter().println(result.toString());
			}else {
				response.getWriter().println(result.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/edit")
	public String edit(){
		return "usr-edit";
	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam(value = "x", required = false) String x,
			@RequestParam(value = "y", required = false) String y,
            @RequestParam(value = "h", required = false) String h,
            @RequestParam(value = "w", required = false) String w,
            @RequestParam(value = "imgFile") MultipartFile imageFile) throws Exception{
		System.out.println("==========Start=============");
		String realPath = "D:/myFile/wsp/resource/uploadImages/head/";
		//String sourcePath = "resources/uploadImages/";
		if(imageFile != null){
			if(FileUploadUtil.allowUpload(imageFile.getContentType())){
				Usr_info loginUser = (Usr_info) request.getSession().getAttribute("loginUser");
				String fileName = FileUploadUtil.rename(imageFile.getOriginalFilename(), loginUser.getId());
				int end = fileName.lastIndexOf(".");
				String saveName = fileName.substring(0, end);
				File dir = new File(realPath);
				if(!dir.exists()){
					dir.mkdirs();
				}
				File file = new File(dir, saveName + "_src.jpg");
				imageFile.transferTo(file);
				String srcImageFile = realPath + saveName;
				int imageX = Integer.parseInt(x);
                int imageY = Integer.parseInt(y);
                int imageH = Integer.parseInt(h);
                int imageW = Integer.parseInt(w);
                //这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImageCut.imgCut(srcImageFile, imageX, imageY, imageW, imageH);
                System.out.println("==========imageCutEnd=============");
                //request.getSession().setAttribute("imgSrc", sourcePath + saveName + "_src.jpg");//成功之后显示用
                //request.getSession().setAttribute("imgCut", sourcePath + saveName + "_cut.jpg");//成功之后显示用
			}
		}
		return "usr-edit";
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpSession session){
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
}
