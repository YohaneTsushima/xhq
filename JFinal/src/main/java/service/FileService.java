package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileService {

	/**
	 * 复制上传的文件到服务器
	 * @param source
	 * @param target
	 */
	public void fileChannelCopy(File source, File target){
		FileInputStream fs = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fs = new FileInputStream(source);
			fo = new FileOutputStream(target);
			in = fs.getChannel(); //得到相应的文件通道
			out = fo.getChannel(); //同上
			in.transferTo(0, in.size(), out); // 连接两个通道，并且从in通道读取，然后写入out通道	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(fs != null)
					fs.close();
				if(in != null)
					in.close();
				if(fo != null)
					fo.close();
				if(out != null)
					out.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
