package com.aq.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
}
