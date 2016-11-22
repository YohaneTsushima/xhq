package com.aq.biz.impl;

import java.util.Arrays;
import java.util.List;

public class FileUploadUtil {

	public static final List<String> ALLOW_TYPES = Arrays.asList(
			"image/jpg","image/jpeg","image/png","image/gif"
	);
	
	//重命名
	public static String rename(String fileName, int id){

		 int i = fileName.lastIndexOf(".");
         String str = fileName.substring(i);
         fileName = id + "_head";
         return fileName + str;
	}
	
	//校验文件类型是否是被允许的
	public static boolean allowUpload(String postfix){
		return ALLOW_TYPES.contains(postfix);
	}
}
