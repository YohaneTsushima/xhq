package service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jfinal.upload.UploadFile;

public class FileUploadUtil {
	public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpg","image/jpeg","image/png","image/gif");
	
	/**
	 * 重命名文件名
	 * @param fileName
	 * @param id
	 * @return
	 */
	public static String rename(String fileName, int id){
		int ext = fileName.lastIndexOf(".");
		String str = fileName.substring(ext);
		fileName = id + "_head";
		return fileName + str;
	}
	
	
	/**
	 * 校验文件类型是否被允许上传
	 * @param postfix
	 * @return
	 */
	public static boolean allowUpload(String postfix){
		return ALLOW_TYPES.contains(postfix);
	}
	
	/**
	 * 上传头像
	 * @param uploadFile
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 * @param id
	 * @param path
	 */
	public void uploadHeadImage(UploadFile uploadFile, int x1, int y1, int width, int height, int id, String srcPath, String targetPath){

		try {
			
			File dir = new File(srcPath + "/");
			if(!dir.exists()){
				dir.mkdirs();
			} 
			
			ImageUtil.imgCut(srcPath + "/" + uploadFile.getOriginalFileName(), id,  x1, y1, width, height, targetPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
