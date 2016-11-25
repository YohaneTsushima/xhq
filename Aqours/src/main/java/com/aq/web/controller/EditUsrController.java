package com.aq.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aq.biz.impl.FileUploadUtil;
import com.aq.biz.impl.ImageCut;
import com.aq.entity.Usr_info;

@Controller
@RequestMapping("/edit_usr")
public class EditUsrController {

	@RequestMapping("/edit")
	public String edit(){
		return "usr-edit";
	}
	
	@RequestMapping(value = "/editImage")
	public String editImage(Model model){
		model.addAttribute("action", "editImage");
		return "usr-edit";
	}
	
	@RequestMapping(value = "/menu")
	public String menu(){
		return "menu";
	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public String uploadImage(HttpServletRequest request,
			@RequestParam(value = "x1", required = false) String x1,
			@RequestParam(value = "y1", required = false) String y1,
            @RequestParam(value = "height", required = false) String height,
            @RequestParam(value = "width", required = false) String width,
            @RequestParam(value = "imgFile") MultipartFile imageFile) throws Exception{
		System.out.println("==========Start=============");
		String realPath = request.getSession().getServletContext().getRealPath("/images/heads/") + "/";
		
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
				int imageX = Integer.parseInt(x1);
                int imageY = Integer.parseInt(y1);
                int imageH = Integer.parseInt(height);
                int imageW = Integer.parseInt(width);
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
}
